package com.aluracursos.ForoHub_desafio_JavaSpring.service;

import com.aluracursos.ForoHub_desafio_JavaSpring.dto.UsuarioRequestDTO;
import com.aluracursos.ForoHub_desafio_JavaSpring.model.Usuario;
import com.aluracursos.ForoHub_desafio_JavaSpring.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        System.out.println("Buscando usuario con email: " + email);
        Usuario usuario = usuarioRepository.findByCorreoElectronico(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el correo: " + email));

        System.out.println("Usuario encontrado: " + usuario.getCorreoElectronico());
        System.out.println("Contraseña almacenada (encriptada): " + usuario.getContrasena());

        return User.builder()
                .username(usuario.getCorreoElectronico())
                .password(usuario.getContrasena())
                .build();
    }

    public void registrarUsuario(@Valid UsuarioRequestDTO usuarioRequest) {
        // Verificar si ya existe un usuario con el mismo correo electrónico
        if (usuarioRepository.findByCorreoElectronico(usuarioRequest.correoElectronico()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un usuario registrado con ese correo electrónico.");
        }

        // Crear una entidad Usuario y encriptar la contraseña
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(usuarioRequest.nombre());
        nuevoUsuario.setCorreoElectronico(usuarioRequest.correoElectronico());
        nuevoUsuario.setContrasena(passwordEncoder.encode(usuarioRequest.contrasena()));

        // Guardar el usuario en la base de datos
        usuarioRepository.save(nuevoUsuario);
    }

}

