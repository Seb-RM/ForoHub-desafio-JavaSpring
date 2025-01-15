package com.aluracursos.ForoHub_desafio_JavaSpring.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "respuesta")
@Entity(name = "Respuesta")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    @Column(name = "topico_id", nullable = false)
    private Integer topicoId;  // Cambiado a Integer para referenciar el ID de Topico

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "autor_id", nullable = false)
    private Integer autorId;  // Cambiado a Integer para referenciar el ID de Usuario

    @Column(nullable = false)
    private Boolean solucion;

    // Constructor sin argumentos
    public Respuesta() {
    }

    // Constructor con argumentos
    public Respuesta(int id, String mensaje, Integer topicoId, LocalDateTime fechaCreacion, Integer autorId, Boolean solucion) {
        this.id = id;
        this.mensaje = mensaje;
        this.topicoId = topicoId;
        this.fechaCreacion = fechaCreacion;
        this.autorId = autorId;
        this.solucion = solucion;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getTopicoId() {
        return topicoId;
    }

    public void setTopicoId(Integer topicoId) {
        this.topicoId = topicoId;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getAutorId() {
        return autorId;
    }

    public void setAutorId(Integer autorId) {
        this.autorId = autorId;
    }

    public Boolean getSolucion() {
        return solucion;
    }

    public void setSolucion(Boolean solucion) {
        this.solucion = solucion;
    }

    @Override
    public String toString() {
        return "Respuesta{" +
                "id=" + id +
                ", mensaje='" + mensaje + '\'' +
                ", topicoId=" + topicoId +
                ", fechaCreacion=" + fechaCreacion +
                ", autorId=" + autorId +
                ", solucion=" + solucion +
                '}';
    }
}



