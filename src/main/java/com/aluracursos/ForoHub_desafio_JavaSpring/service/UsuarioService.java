package com.aluracursos.ForoHub_desafio_JavaSpring.service;

import com.aluracursos.ForoHub_desafio_JavaSpring.dto.UsuarioRequestDTO;
import com.aluracursos.ForoHub_desafio_JavaSpring.model.Usuario;
import com.aluracursos.ForoHub_desafio_JavaSpring.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreoElectronico(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el correo: " + email));

        return User.builder()
                .username(usuario.getCorreoElectronico())
                .password(usuario.getContrasena())
                .authorities(Collections.emptyList()) // Asegúrate de agregar roles si es necesario
                .build();
    }

    @Transactional
    public void registrarUsuario(@Valid UsuarioRequestDTO usuarioRequest) {
        if (usuarioRepository.findByCorreoElectronico(usuarioRequest.correoElectronico()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un usuario registrado con ese correo electrónico.");
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(usuarioRequest.nombre());
        nuevoUsuario.setCorreoElectronico(usuarioRequest.correoElectronico());
        nuevoUsuario.setContrasena(passwordEncoder.encode(usuarioRequest.contrasena()));

        usuarioRepository.save(nuevoUsuario);
    }
}


