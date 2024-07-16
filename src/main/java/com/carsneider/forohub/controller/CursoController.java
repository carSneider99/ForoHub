package com.carsneider.forohub.controller;

import com.carsneider.forohub.dto.CursoDTO;
import com.carsneider.forohub.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @GetMapping
    @Operation(summary = "Obtiene el listado de cursos")
    public ResponseEntity<Page<CursoDTO>> listarCursos(Pageable paginacion){
        return ResponseEntity.ok(cursoService.listar(paginacion));
    }

    @PostMapping
    @Operation(summary = "Registra un nuevo curso")
    public ResponseEntity<CursoDTO> agregarCurso(@RequestBody @Valid CursoDTO cursoDTO, UriComponentsBuilder uriComponentsBuilder){
        CursoDTO cursoGuardado = cursoService.guardarCurso(cursoDTO);
        URI url = uriComponentsBuilder. path("/cursos/{id}").buildAndExpand(cursoGuardado.id()).toUri();
        return ResponseEntity.created(url).body(cursoGuardado);
    }
}
