package com.carsneider.forohub.entity;

import com.carsneider.forohub.dto.CursoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cursos")
@Getter
@Setter
@NoArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public Curso (CursoDTO cursoDTO){
        this.id = cursoDTO.id();
        this.nombre = cursoDTO.nombre();
    }
}