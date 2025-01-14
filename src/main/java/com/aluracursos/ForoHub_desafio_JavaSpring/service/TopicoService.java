package com.aluracursos.ForoHub_desafio_JavaSpring.service;

import com.aluracursos.ForoHub_desafio_JavaSpring.dto.TopicoRequestDTO;
import com.aluracursos.ForoHub_desafio_JavaSpring.dto.TopicoResponseDTO;
import com.aluracursos.ForoHub_desafio_JavaSpring.model.Curso;
import com.aluracursos.ForoHub_desafio_JavaSpring.model.Topico;
import com.aluracursos.ForoHub_desafio_JavaSpring.model.Usuario;
import org.springframework.stereotype.Service;

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

        Topico topico = Topico.builder()
                .titulo(requestDTO.getTitulo())
                .mensaje(requestDTO.getMensaje())
                .autor(autor)
                .curso(curso)
                .status(StatusTopico.ACTIVO) // Define un estado predeterminado
                .build();

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

