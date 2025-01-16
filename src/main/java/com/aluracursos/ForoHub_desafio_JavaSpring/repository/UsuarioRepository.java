package com.aluracursos.ForoHub_desafio_JavaSpring.repository;

import com.aluracursos.ForoHub_desafio_JavaSpring.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreoElectronico(String correoElectronico);
}
