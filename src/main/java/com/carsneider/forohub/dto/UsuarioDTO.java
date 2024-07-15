package com.carsneider.forohub.dto;

import com.carsneider.forohub.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

public record UsuarioDTO(
        @NotNull @NumberFormat Long id,
        @NotBlank String nombre,
        @NotNull @Email String email,
        @NotNull String password) {

    public UsuarioDTO(Usuario user){
        this(user.getId(), user.getNombre(), user.getEmail(), user.getPassword());
    }
}