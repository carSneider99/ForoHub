package com.carsneider.forohub.dto;

import com.carsneider.forohub.entity.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

public record CursoDTO (
        @NotNull @NumberFormat Long id,
        @NotBlank String nombre
) {
    public CursoDTO(Curso curso) {
        this(curso.getId(), curso.getNombre());
    }
}