package com.aluracursos.ForoHub_desafio_JavaSpring.controller;

import com.aluracursos.ForoHub_desafio_JavaSpring.dto.UsuarioRequestDTO;
import com.aluracursos.ForoHub_desafio_JavaSpring.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<String> registrarUsuario(@RequestBody @Valid UsuarioRequestDTO usuarioRequest) {
        try {
            usuarioService.registrarUsuario(usuarioRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente.");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el usuario.");
        }
    }
}

