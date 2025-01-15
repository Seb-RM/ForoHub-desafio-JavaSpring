package com.aluracursos.ForoHub_desafio_JavaSpring.exception;

public class DuplicateTopicException extends RuntimeException {
    public DuplicateTopicException(String message) {
        super(message);
    }
}

