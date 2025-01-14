package com.aluracursos.ForoHub_desafio_JavaSpring.model;

import jakarta.persistence.*;

@Table(name = "perfil")
@Entity(name = "Perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String nombre;

    // Constructor sin argumentos
    public Perfil() {
    }

    // Constructor con argumentos
    public Perfil(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}


