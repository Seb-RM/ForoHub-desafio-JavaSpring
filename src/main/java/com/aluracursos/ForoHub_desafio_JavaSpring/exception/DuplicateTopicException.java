package com.aluracursos.ForoHub_desafio_JavaSpring.exception;

import org.springframework.http.HttpStatus;

public class DuplicateTopicException extends RuntimeException {
    private final HttpStatus status;

    public DuplicateTopicException(String message) {
        super(message);
        this.status = HttpStatus.CONFLICT; // Código 409 para conflictos
    }

    public DuplicateTopicException(String message, Throwable cause) {
        super(message, cause);
        this.status = HttpStatus.CONFLICT;
    }

    public HttpStatus getStatus() {
        return status;
    }
}

