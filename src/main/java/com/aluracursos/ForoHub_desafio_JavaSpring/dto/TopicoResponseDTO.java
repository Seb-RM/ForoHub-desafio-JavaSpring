package com.aluracursos.ForoHub_desafio_JavaSpring.dto;

import java.time.LocalDateTime;

public class TopicoResponseDTO {

    private int id;
    private String titulo;
    private String mensaje;
    private AutorDTO autor;
    private CursoDTO curso;
    private String status;  // Nuevo campo para el estado
    private LocalDateTime fechaCreacion;// Modificado para reflejar el ID del curso

    // Constructor
    public TopicoResponseDTO(int id, String titulo, String mensaje, AutorDTO autor, CursoDTO curso, String status, LocalDateTime fechaCreacion) {
        this.id = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.autor = autor;
        this.curso = curso;
        this.status = status;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public AutorDTO getAutor() {
        return autor;
    }

    public void setAutor(AutorDTO autor) {
        this.autor = autor;
    }

    public CursoDTO getCurso() {
        return curso;
    }

    public void setCurso(CursoDTO curso) {
        this.curso = curso;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
