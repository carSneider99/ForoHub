package com.carsneider.forohub.dto;

import com.carsneider.forohub.entity.Topico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

public record TopicoDTO(
        @NotNull @NumberFormat Long id,
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull @NumberFormat Long idAutor,
        @NotNull @NumberFormat Long idCurso
) {
    public TopicoDTO(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor().getId(), topico.getCurso().getId());
    }
}