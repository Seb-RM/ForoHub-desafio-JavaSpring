package com.aluracursos.ForoHub_desafio_JavaSpring.dto;

import java.time.LocalDateTime;

public class TopicoResponseDTO {

    private int id;
    private String titulo;
    private String mensaje;
    private Integer autorId;  // Modificado para reflejar el ID del autor
    private Integer cursoId;
    private String status;  // Nuevo campo para el estado
    private LocalDateTime fechaCreacion;// Modificado para reflejar el ID del curso

    // Constructor
    public TopicoResponseDTO(int id, String titulo, String mensaje, Integer autorId, Integer cursoId, String status, LocalDateTime fechaCreacion) {
        this.id = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.autorId = autorId;
        this.cursoId = cursoId;
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
