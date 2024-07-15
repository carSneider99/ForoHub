package com.carsneider.forohub.repository;

import com.carsneider.forohub.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso,Long> {
    boolean existsByNombre(String nombre);
}
