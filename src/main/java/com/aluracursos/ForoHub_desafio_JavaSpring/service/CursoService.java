package com.aluracursos.ForoHub_desafio_JavaSpring.service;

import com.aluracursos.ForoHub_desafio_JavaSpring.exception.CursoNoEncontradoException;
import com.aluracursos.ForoHub_desafio_JavaSpring.model.Curso;
import com.aluracursos.ForoHub_desafio_JavaSpring.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    // Suponiendo que tienes un repositorio para acceder a los cursos
    @Autowired
    private CursoRepository cursoRepository;

    public Integer buscarIdPorNombre(String cursoNombre) {
        Curso curso = cursoRepository.findByNombre(cursoNombre);
        if (curso != null) {
            return curso.getId();  // Devuelve el ID del curso si se encuentra
        } else {
            throw new CursoNoEncontradoException("Curso no encontrado con el nombre: " + cursoNombre);
        }
    }
}
