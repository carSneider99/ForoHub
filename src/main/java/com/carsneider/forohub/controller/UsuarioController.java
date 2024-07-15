package com.carsneider.forohub.controller;

import com.carsneider.forohub.dto.UsuarioDTO;
import com.carsneider.forohub.dto.UsuarioSinPasswordDTO;
import com.carsneider.forohub.service.UsuarioService;
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
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Page<UsuarioSinPasswordDTO>> listar(Pageable paginacion){
        return ResponseEntity.ok(usuarioService.listarUsuarios(paginacion));
    }

    @PostMapping
    public ResponseEntity<UsuarioSinPasswordDTO> agregarUsuario(@RequestBody @Valid UsuarioDTO userDTO, UriComponentsBuilder uriComponentsBuilder){
        UsuarioSinPasswordDTO userGuardado = usuarioService.guardarUsuario(userDTO);
        URI url = uriComponentsBuilder. path("/usuarios/{id}").buildAndExpand(userGuardado.id()).toUri();
        return ResponseEntity.created(url).body(userGuardado);
    }
}