package com.carsneider.forohub.repository;

import com.carsneider.forohub.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    boolean existsByNombre(String nombre);

    boolean existsByEmail(String email);

    UserDetails findByEmail(String username);
}