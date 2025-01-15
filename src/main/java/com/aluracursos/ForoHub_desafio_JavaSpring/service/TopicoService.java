package com.aluracursos.ForoHub_desafio_JavaSpring.service;

import com.aluracursos.ForoHub_desafio_JavaSpring.dto.TopicoRequestDTO;
import com.aluracursos.ForoHub_desafio_JavaSpring.dto.TopicoResponseDTO;
import com.aluracursos.ForoHub_desafio_JavaSpring.exception.ResourceNotFoundException;
import com.aluracursos.ForoHub_desafio_JavaSpring.model.Curso;
import com.aluracursos.ForoHub_desafio_JavaSpring.model.StatusTopico;
import com.aluracursos.ForoHub_desafio_JavaSpring.model.Topico;
import com.aluracursos.ForoHub_desafio_JavaSpring.model.Usuario;
import com.aluracursos.ForoHub_desafio_JavaSpring.repository.CursoRepository;
import com.aluracursos.ForoHub_desafio_JavaSpring.repository.TopicoRepository;
import com.aluracursos.ForoHub_desafio_JavaSpring.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public TopicoService(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository, CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    public TopicoResponseDTO registrarTopico(TopicoRequestDTO requestDTO) {
        // Verificar si el autor existe mediante su ID
        usuarioRepository.findById(requestDTO.getAutorId())
                .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado con ID: " + requestDTO.getAutorId()));

        // Verificar si el curso existe mediante su ID
        cursoRepository.findById(requestDTO.getCursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + requestDTO.getCursoId()));

        // Crear el nuevo Topico con los ID de autor y curso
        Topico topico = new Topico(
                requestDTO.getTitulo(),
                requestDTO.getMensaje(),
                StatusTopico.ACTIVO,
                requestDTO.getAutorId(), // Usar el ID del autor
                requestDTO.getCursoId()   // Usar el ID del curso
        );

        // Guardar el Topico en la base de datos
        Topico topicoGuardado = topicoRepository.save(topico);

        // Crear y retornar el DTO de respuesta con la información del nuevo Topico
        return new TopicoResponseDTO(
                topicoGuardado.getId(),
                topicoGuardado.getTitulo(),
                topicoGuardado.getMensaje(),
                topicoGuardado.getAutorId(),  // Retornar el ID del autor
                topicoGuardado.getCursoId(),  // Retornar el ID del curso
                topicoGuardado.getStatus().name(),  // Obtener el estado como String
                topicoGuardado.getFechaCreacion()
        );
    }

    public TopicoResponseDTO obtenerTopicoPorId(int id) {
        // Obtener el Topico desde el repositorio
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico no encontrado con ID: " + id));

        // Crear y retornar el DTO de respuesta con la información del Topico
        return new TopicoResponseDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutorId(),  // Retornar el ID del autor
                topico.getCursoId(),   // Retornar el ID del curso
                topico.getStatus().name(),  // Obtener el estado como String
                topico.getFechaCreacion()
        );
    }
}


