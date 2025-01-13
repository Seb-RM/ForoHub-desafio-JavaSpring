package com.aluracursos.ForoHub_desafio_JavaSpring.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "curso")
@Entity(name = "Curso")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String categoria;
}

