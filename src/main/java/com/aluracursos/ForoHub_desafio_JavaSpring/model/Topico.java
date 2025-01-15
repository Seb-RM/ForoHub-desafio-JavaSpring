package com.aluracursos.ForoHub_desafio_JavaSpring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topico")
@Entity(name = "Topico")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true, length = 255)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTopico status;

    // Cambiar los campos de tipo entidad a campos de tipo Integer (ID)
    @NotNull(message = "El autor no puede ser nulo")
    @Column(name = "autor_id", nullable = false)
    private Integer autorId;

    @NotNull(message = "El curso no puede ser nulo")
    @Column(name = "curso_id", nullable = false)
    private Integer cursoId;

    @OneToMany(mappedBy = "topicoId", cascade = CascadeType.ALL)
    private List<Respuesta> respuestas = new ArrayList<>();

    // Constructor sin argumentos
    public Topico() {
    }

    // Constructor con parámetros
    public Topico(String titulo, String mensaje, StatusTopico status, Integer autorId, Integer cursoId) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.status = status != null ? status : StatusTopico.ACTIVO;
        this.autorId = autorId;
        this.cursoId = cursoId;
        this.fechaCreacion = LocalDateTime.now();
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public StatusTopico getStatus() {
        return status;
    }

    public void setStatus(StatusTopico status) {
        this.status = status;
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

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    // Método prePersist para inicializar fechaCreacion
    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Topico{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", status=" + status +
                ", autorId=" + autorId +
                ", cursoId=" + cursoId +
                ", respuestas=" + respuestas +
                '}';
    }
}