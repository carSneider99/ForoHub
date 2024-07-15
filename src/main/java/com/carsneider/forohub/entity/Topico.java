package com.carsneider.forohub.entity;

import com.carsneider.forohub.dto.TopicoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="topicos")
@Getter
@Setter
@NoArgsConstructor
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso")
    private Curso curso;

    public Topico(TopicoDTO topicoDTO, Usuario autor, Curso curso) {
        this.titulo = topicoDTO.titulo();
        this.mensaje = topicoDTO.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.estado = Estado.ACTIVO;
        this.autor = autor;
        this.curso = curso;
    }
}