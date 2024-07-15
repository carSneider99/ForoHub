package com.carsneider.forohub.service;

import com.carsneider.forohub.dto.UsuarioDTO;
import com.carsneider.forohub.dto.UsuarioSinPasswordDTO;
import com.carsneider.forohub.entity.Usuario;
import com.carsneider.forohub.infra.errores.ValidacionDeIntegridad;
import com.carsneider.forohub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<UsuarioSinPasswordDTO> listarUsuarios(Pageable paginacion) {
        return usuarioRepository.findAll(paginacion).map(UsuarioSinPasswordDTO::new);
    }

    public UsuarioSinPasswordDTO guardarUsuario(UsuarioDTO userDTO) {
        if (userDTO.id() != -1)
            throw new ValidacionDeIntegridad("Id para agregar usuario inválido");

        if (usuarioRepository.existsByNombre(userDTO.nombre()))
            throw new ValidacionDeIntegridad("Ya existe un usuario regristado con el nombre: " + userDTO.nombre());

        if (usuarioRepository.existsByEmail(userDTO.email()))
            throw new ValidacionDeIntegridad("Ya existe un usuario regristado con el email: " + userDTO.email());

        Usuario usuario = new Usuario(userDTO);
        usuario.setPassword(passwordEncoder.encode(userDTO.password()));

        Usuario userGuardado = usuarioRepository.save(usuario);
        return new UsuarioSinPasswordDTO(userGuardado);
    }
}