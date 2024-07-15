package com.carsneider.forohub.controller;

import com.carsneider.forohub.dto.TopicoDTO;
import com.carsneider.forohub.service.TopicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {
    @Autowired
    private TopicoService topicoService;

    @GetMapping
    @Operation(summary = "Obtiene el listado de los tópicos")
    public ResponseEntity<Page<TopicoDTO>> listarTopicos(Pageable paginacion){
        return ResponseEntity.ok(topicoService.listar(paginacion));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene el tópico al especificar el Id")
    public ResponseEntity<TopicoDTO> obtenerTopico(@PathVariable Long id){
        return ResponseEntity.ok(topicoService.obtenerTopico(id));
    }

    @PostMapping
    @Operation(summary = "Registra un nuevo tópico en la BBDD")
    public ResponseEntity<TopicoDTO> registrarTopico(@RequestBody @Valid TopicoDTO topicoDTO){
        return ResponseEntity.ok(topicoService.agregar(topicoDTO));
    }

    @PutMapping
    @Operation(summary = "Actualiza un tópico")
    public ResponseEntity<TopicoDTO> actualizarTopico(@RequestBody @Valid TopicoDTO topicoDTO){
        return ResponseEntity.ok(topicoService.actualizarTopico(topicoDTO));
    }

    @DeleteMapping("/{id")
    @Operation(summary = "Elimina un tópcio al especificar el Id")
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}