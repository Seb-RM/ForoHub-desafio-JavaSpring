package com.aluracursos.ForoHub_desafio_JavaSpring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDTO(
        @NotBlank(message = "El nombre no puede estar vacío.") String nombre,
        @Email(message = "El correo electrónico no es válido.") String correoElectronico,
        @Size(min = 5, message = "La contraseña debe tener al menos 5 caracteres.") String contrasena
) {}

