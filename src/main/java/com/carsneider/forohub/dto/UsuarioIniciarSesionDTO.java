package com.carsneider.forohub.dto;

import com.carsneider.forohub.entity.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UsuarioIniciarSesionDTO(
        @NotNull @Email String email,
        @NotNull String password) {

    public UsuarioIniciarSesionDTO(Usuario user){
        this(user.getEmail(), user.getPassword());
    }
}