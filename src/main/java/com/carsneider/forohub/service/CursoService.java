package com.carsneider.forohub.service;

import com.carsneider.forohub.dto.CursoDTO;
import com.carsneider.forohub.entity.Curso;
import com.carsneider.forohub.infra.errores.ValidacionDeIntegridad;
import com.carsneider.forohub.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public Page<CursoDTO> listar(Pageable paginacion) {
        return cursoRepository.findAll(paginacion).map(CursoDTO::new);
    }

    public CursoDTO guardarCurso(CursoDTO cursoDTO) {
        if (cursoDTO.id() != -1)
            throw new ValidacionDeIntegridad("Id para agregar curso inválido");

        if (cursoRepository.existsByNombre(cursoDTO.nombre()))
            throw new ValidacionDeIntegridad("Ya existe un curso regristado con el nombre: " + cursoDTO.nombre());

        Curso cursoGuardado = cursoRepository.save(new Curso(cursoDTO));
        return new CursoDTO(cursoGuardado);
    }
}