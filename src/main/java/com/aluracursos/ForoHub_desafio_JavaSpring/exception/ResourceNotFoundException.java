package com.aluracursos.ForoHub_desafio_JavaSpring.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
