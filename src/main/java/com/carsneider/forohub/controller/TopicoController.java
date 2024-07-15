package com.carsneider.forohub.controller;

import com.carsneider.forohub.dto.TopicoDTO;
import com.carsneider.forohub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoService topicoService;

    @GetMapping
    public ResponseEntity<List<TopicoDTO>> listarTopicos(){
        return null;
    }

    @PostMapping
    public ResponseEntity<TopicoDTO> registrarTopico(@RequestBody @Valid TopicoDTO topicoDTO){
        return ResponseEntity.ok(topicoService.agregar(topicoDTO));

    }
}
