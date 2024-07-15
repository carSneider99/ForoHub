package com.carsneider.forohub.controller;

import com.carsneider.forohub.dto.TopicoDTO;
import com.carsneider.forohub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoService topicoService;

    @GetMapping
    public ResponseEntity<Page<TopicoDTO>> listarTopicos(Pageable paginacion){
        return ResponseEntity.ok(topicoService.listar(paginacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDTO> obtenerTopico(@PathVariable Long id){
        return ResponseEntity.ok(topicoService.obtenerTopico(id));
    }

    @PostMapping
    public ResponseEntity<TopicoDTO> registrarTopico(@RequestBody @Valid TopicoDTO topicoDTO){
        return ResponseEntity.ok(topicoService.agregar(topicoDTO));
    }

    @PutMapping
    public ResponseEntity<TopicoDTO> actualizarTopico(@RequestBody @Valid TopicoDTO topicoDTO){
        return ResponseEntity.ok(topicoService.actualizarTopico(topicoDTO));
    }

    @DeleteMapping("/{id")
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}