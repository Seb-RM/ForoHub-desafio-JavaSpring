package com.aluracursos.ForoHub_desafio_JavaSpring.dto;

import jakarta.validation.constraints.NotBlank;

public class TopicoRequestDTO {

    @NotBlank(message = "El título es obligatorio.")
    private String titulo;

    @NotBlank(message = "El mensaje es obligatorio.")
    private String mensaje;

    @NotBlank(message = "El autor es obligatorio.")
    private Integer autorId;

    @NotBlank(message = "El curso es obligatorio.")
    private Integer cursoId;

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
