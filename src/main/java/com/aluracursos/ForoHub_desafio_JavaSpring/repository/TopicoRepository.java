package com.aluracursos.ForoHub_desafio_JavaSpring.repository;

import com.aluracursos.ForoHub_desafio_JavaSpring.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Integer> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
}
