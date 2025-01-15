package com.aluracursos.ForoHub_desafio_JavaSpring.dto;

import com.aluracursos.ForoHub_desafio_JavaSpring.model.StatusTopico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TopicoRequestDTO {

    @NotNull(message = "El título no puede ser nulo")
    @NotBlank(message = "El título no puede estar vacío ni contener solo espacios")
    private String titulo;

    @NotNull(message = "El mensaje no puede ser nulo")
    @NotBlank(message = "El mensaje no puede estar vacío ni contener solo espacios")
    private String mensaje;

    @NotNull(message = "El autor no puede ser nulo")
    private Integer autorId;

    @NotNull(message = "El curso no puede ser nulo")
    private Integer cursoId;

    // Getters y setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getAutorId() {
        return autorId;
    }

    public void setAutorId(Integer autorId) {
        this.autorId = autorId;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

}

