package com.aluracursos.ForoHub_desafio_JavaSpring.service;

import com.aluracursos.ForoHub_desafio_JavaSpring.dto.AutorDTO;
import com.aluracursos.ForoHub_desafio_JavaSpring.dto.CursoDTO;
import com.aluracursos.ForoHub_desafio_JavaSpring.dto.TopicoRequestDTO;
import com.aluracursos.ForoHub_desafio_JavaSpring.dto.TopicoResponseDTO;
import com.aluracursos.ForoHub_desafio_JavaSpring.exception.DuplicateTopicException;
import com.aluracursos.ForoHub_desafio_JavaSpring.exception.ResourceNotFoundException;
import com.aluracursos.ForoHub_desafio_JavaSpring.model.Curso;
import com.aluracursos.ForoHub_desafio_JavaSpring.model.StatusTopico;
import com.aluracursos.ForoHub_desafio_JavaSpring.model.Topico;
import com.aluracursos.ForoHub_desafio_JavaSpring.model.Usuario;
import com.aluracursos.ForoHub_desafio_JavaSpring.repository.CursoRepository;
import com.aluracursos.ForoHub_desafio_JavaSpring.repository.TopicoRepository;
import com.aluracursos.ForoHub_desafio_JavaSpring.repository.UsuarioRepository;
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
        // Verificar si el autor existe mediante su ID
        Usuario autor = usuarioRepository.findById(requestDTO.getAutorId())
                .orElseThrow(() -> new ResourceNotFoundException("El autor con ID " + requestDTO.getAutorId() + " no fue encontrado."));

        // Verificar si el curso existe mediante su ID
        Curso curso = cursoRepository.findById(requestDTO.getCursoId())
                .orElseThrow(() -> new ResourceNotFoundException("El curso con ID " + requestDTO.getCursoId() + " no fue encontrado."));

        boolean existeTopico = topicoRepository.existsByTituloAndMensaje(requestDTO.getTitulo(), requestDTO.getMensaje());
        if (existeTopico) {
            throw new DuplicateTopicException(String.format("No se pudo crear el tópico porque ya existe un tópico con el título '%s' y el mensaje '%s'.",
                    requestDTO.getTitulo(), requestDTO.getMensaje()));
        }

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

        AutorDTO autorDTO = new AutorDTO(autor.getId(), autor.getNombre());
        CursoDTO cursoDTO = new CursoDTO(curso.getId(), curso.getNombre());

        // Crear y retornar el DTO de respuesta con la información del nuevo Topico
        return new TopicoResponseDTO(
                topicoGuardado.getId(),
                topicoGuardado.getTitulo(),
                topicoGuardado.getMensaje(),
                autorDTO,  // Datos completos del autor
                cursoDTO, // Retornar el ID del curso
                topicoGuardado.getStatus().name(),  // Obtener el estado como String
                topicoGuardado.getFechaCreacion()
        );
    }

}


