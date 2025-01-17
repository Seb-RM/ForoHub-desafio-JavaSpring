package com.aluracursos.ForoHub_desafio_JavaSpring.controller;

import com.aluracursos.ForoHub_desafio_JavaSpring.dto.TopicoRequestDTO;
import com.aluracursos.ForoHub_desafio_JavaSpring.dto.TopicoResponseDTO;
import com.aluracursos.ForoHub_desafio_JavaSpring.exception.TopicoNoEncontradoException;
import com.aluracursos.ForoHub_desafio_JavaSpring.service.CursoService;
import com.aluracursos.ForoHub_desafio_JavaSpring.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService topicoService;
    private final CursoService cursoService;

    public TopicoController(TopicoService topicoService, CursoService cursoService) {
        this.topicoService = topicoService;
        this.cursoService = cursoService;
    }

    @PostMapping
    public ResponseEntity<TopicoResponseDTO> registrarTopico(@RequestBody @Valid TopicoRequestDTO requestDTO) {
        TopicoResponseDTO responseDTO = topicoService.registrarTopico(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<TopicoResponseDTO>> listarTopicos(
            @RequestParam(required = false) String cursoNombre,
            @RequestParam(required = false) Integer anio,
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable pageable) {

        Integer cursoId = null;

        if (cursoNombre != null) {
            cursoId = cursoService.buscarIdPorNombre(cursoNombre);  // Buscar el ID del curso a partir del nombre
        }
        Page<TopicoResponseDTO> topicos = topicoService.listarTopicos(cursoId, anio, pageable);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponseDTO> obtenerTopicoPorId(@PathVariable int id) {
        Optional<TopicoResponseDTO> topico = topicoService.obtenerTopicoPorId(id);

        if (topico.isEmpty()) {
            throw new TopicoNoEncontradoException("Tópico con ID " + id + " no encontrado.");
        }

        return ResponseEntity.ok(topico.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoResponseDTO> actualizarTopico(
            @PathVariable int id,
            @RequestBody @Valid TopicoRequestDTO topicoRequest) {

        TopicoResponseDTO topicoActualizado = topicoService.actualizarTopico(id, topicoRequest);

        return ResponseEntity.ok(topicoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Integer id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build(); // Responde con código 204 No Content
    }
}

