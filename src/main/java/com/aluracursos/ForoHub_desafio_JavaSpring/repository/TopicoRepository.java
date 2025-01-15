package com.aluracursos.ForoHub_desafio_JavaSpring.repository;

import com.aluracursos.ForoHub_desafio_JavaSpring.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Integer> {

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    @Query("SELECT t FROM Topico t WHERE t.cursoId = :cursoId")
    Page<Topico> findByCursoId(@Param("cursoId") Integer cursoId, Pageable pageable);


    @Query("SELECT t FROM Topico t WHERE YEAR(t.fechaCreacion) = :anio")
    Page<Topico> findByFechaCreacionYear(@Param("anio") Integer anio, Pageable pageable);

    @Query("SELECT t FROM Topico t WHERE t.cursoId = :cursoId AND YEAR(t.fechaCreacion) = :anio")
    Page<Topico> findByCursoIdAndFechaCreacionYear(@Param("cursoId") Integer cursoId, @Param("anio") Integer anio, Pageable pageable);

}
