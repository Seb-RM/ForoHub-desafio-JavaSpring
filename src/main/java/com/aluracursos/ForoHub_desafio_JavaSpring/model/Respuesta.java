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

    @ManyToOne
    @JoinColumn(name = "topico_id", nullable = false)
    private Topico topico;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;

    @Column(nullable = false)
    private Boolean solucion;

    // Constructor sin argumentos
    public Respuesta() {
    }

    // Constructor con argumentos
    public Respuesta(int id, String mensaje, Topico topico, LocalDateTime fechaCreacion, Usuario autor, Boolean solucion) {
        this.id = id;
        this.mensaje = mensaje;
        this.topico = topico;
        this.fechaCreacion = fechaCreacion;
        this.autor = autor;
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

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
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
                ", topico=" + (topico != null ? topico.getId() : null) +
                ", fechaCreacion=" + fechaCreacion +
                ", autor=" + (autor != null ? autor.getId() : null) +
                ", solucion=" + solucion +
                '}';
    }
}


