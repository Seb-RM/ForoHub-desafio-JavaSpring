package com.aluracursos.ForoHub_desafio_JavaSpring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CursoNoEncontradoException extends RuntimeException {

    public CursoNoEncontradoException(String message) {
        super(message);
    }
}
