package com.aluracursos.ForoHub_desafio_JavaSpring.controller;

import com.aluracursos.ForoHub_desafio_JavaSpring.dto.TopicoRequestDTO;
import com.aluracursos.ForoHub_desafio_JavaSpring.dto.TopicoResponseDTO;
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

}

