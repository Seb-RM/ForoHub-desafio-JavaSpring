package com.aluracursos.ForoHub_desafio_JavaSpring.repository;

import com.aluracursos.ForoHub_desafio_JavaSpring.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {}
