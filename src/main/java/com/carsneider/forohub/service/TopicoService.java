package com.carsneider.forohub.service;

import com.carsneider.forohub.dto.TopicoDTO;
import com.carsneider.forohub.entity.Curso;
import com.carsneider.forohub.entity.Topico;
import com.carsneider.forohub.entity.Usuario;
import com.carsneider.forohub.infra.errores.ValidacionDeIntegridad;
import com.carsneider.forohub.repository.CursoRepository;
import com.carsneider.forohub.repository.TopicoRepository;
import com.carsneider.forohub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public TopicoDTO agregar(TopicoDTO topicoDTO) {
        if (topicoDTO.id() != -1)
            throw new ValidacionDeIntegridad("Id para agregar tópico inválido");

        if(topicoRepository.existsByTituloAndMensaje(topicoDTO.titulo(), topicoDTO.mensaje()))
            throw new ValidacionDeIntegridad("Título y mensaje ya encontrados en un tópico");

        if(!cursoRepository.existsById(topicoDTO.idCurso()))
            throw new ValidacionDeIntegridad("Este id para el curso no fue encontrado");

        if(!usuarioRepository.existsById(topicoDTO.idAutor()))
            throw new ValidacionDeIntegridad("Este id para el autor no fue encontrado");

        Usuario autor = usuarioRepository.getReferenceById(topicoDTO.idAutor());
        Curso curso = cursoRepository.getReferenceById(topicoDTO.idCurso());
        Topico topicoGuardado = topicoRepository.save(new Topico(topicoDTO, autor, curso));
        return new TopicoDTO(topicoGuardado);
    }

    public Page<TopicoDTO> listar(Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(TopicoDTO::new);
    }

    public TopicoDTO obtenerTopico(Long id) {
        if (!topicoRepository.existsById(id))
            throw new ValidacionDeIntegridad("Id del tópico no encontrado, valide el id por favor");

        return new TopicoDTO(topicoRepository.getReferenceById(id));
    }

    public TopicoDTO actualizarTopico(TopicoDTO topicoDTO) {
        if (!topicoRepository.existsById(topicoDTO.id()))
            throw new ValidacionDeIntegridad("No existe Tópico, corrija id del tópico a actualizar");

        if(topicoRepository.existsByTituloAndMensaje(topicoDTO.titulo(), topicoDTO.mensaje()))
            throw new ValidacionDeIntegridad("Título y mensaje ya encontrados en un tópico, corrija la actualización");

        if(!cursoRepository.existsById(topicoDTO.idCurso()))
            throw new ValidacionDeIntegridad("Este id para el curso no fue encontrado, corrija para actualizar");

        if(!usuarioRepository.existsById(topicoDTO.idAutor()))
            throw new ValidacionDeIntegridad("Este id para el autor no fue encontrado, corrija para actualizar");

        Usuario autor = usuarioRepository.getReferenceById(topicoDTO.idAutor());
        Curso curso = cursoRepository.getReferenceById(topicoDTO.idCurso());

        Topico topicoActualizar = new Topico(topicoDTO, autor, curso);
        topicoActualizar.setId(topicoDTO.id());
        return new TopicoDTO(topicoRepository.save(topicoActualizar));
    }

    public void eliminarTopico(Long id) {
        if (!topicoRepository.existsById(id))
            throw new ValidacionDeIntegridad("No existe Tópico, corrija id del tópico a eliminar");

        topicoRepository.deleteById(id);
    }
}