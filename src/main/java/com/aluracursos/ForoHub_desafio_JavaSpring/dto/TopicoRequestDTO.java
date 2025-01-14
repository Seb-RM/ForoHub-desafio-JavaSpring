package com.aluracursos.ForoHub_desafio_JavaSpring.dto;

import jakarta.validation.constraints.NotBlank;

public class TopicoRequestDTO {

    @NotBlank(message = "El título es obligatorio.")
    private String titulo;

    @NotBlank(message = "El mensaje es obligatorio.")
    private String mensaje;

    @NotBlank(message = "El autor es obligatorio.")
    private String autor;

    @NotBlank(message = "El curso es obligatorio.")
    private String curso;

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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
