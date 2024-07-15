package com.carsneider.forohub.controller;

import com.carsneider.forohub.dto.UsuarioDTO;
import com.carsneider.forohub.dto.UsuarioSinPasswordDTO;
import com.carsneider.forohub.service.UsuarioService;
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
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Obtiene el listado de los usuarios")
    public ResponseEntity<Page<UsuarioSinPasswordDTO>> listar(Pageable paginacion){
        return ResponseEntity.ok(usuarioService.listarUsuarios(paginacion));
    }

    @PostMapping
    @Operation(summary = "Registra un nuevo usuario")
    public ResponseEntity<UsuarioSinPasswordDTO> agregarUsuario(@RequestBody @Valid UsuarioDTO userDTO, UriComponentsBuilder uriComponentsBuilder){
        UsuarioSinPasswordDTO userGuardado = usuarioService.guardarUsuario(userDTO);
        URI url = uriComponentsBuilder. path("/usuarios/{id}").buildAndExpand(userGuardado.id()).toUri();
        return ResponseEntity.created(url).body(userGuardado);
    }
}