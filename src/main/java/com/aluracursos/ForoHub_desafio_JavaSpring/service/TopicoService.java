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
        Usuario autor = usuarioRepository.findById(requestDTO.getAutorId())
                .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado."));
        Curso curso = cursoRepository.findById(requestDTO.getCursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado."));

        Topico topico = new Topico(
                requestDTO.getTitulo(),
                requestDTO.getMensaje(),
                StatusTopico.ACTIVO,
                autor,
                curso
        );

        Topico topicoGuardado = topicoRepository.save(topico);

        return new TopicoResponseDTO(
                topicoGuardado.getId(),
                topicoGuardado.getTitulo(),
                topicoGuardado.getMensaje(),
                autor.getNombre(),
                curso.getNombre()
        );
    }

    public TopicoResponseDTO obtenerTopicoPorId(int id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico no encontrado con ID: " + id));

        return new TopicoResponseDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}

