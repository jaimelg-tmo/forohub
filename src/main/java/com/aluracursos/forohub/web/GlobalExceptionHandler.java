package com.aluracursos.forohub.web;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new HashMap<>();
        Map<String, String> errors = new HashMap<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            errors.put(fe.getField(), fe.getDefaultMessage());
        }
        body.put("timestamp", Instant.now());
        body.put("status", 400);
        body.put("error", "Bad Request");
        body.put("message", "Datos inválidos");
        body.put("fields", errors);
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraint(ConstraintViolationException ex) {
        Map<String, Object> body = Map.of(
                "timestamp", Instant.now(),
                "status", 400,
                "error", "Bad Request",
                "message", ex.getMessage()
        );
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegal(IllegalArgumentException ex) {
        Map<String, Object> body = Map.of(
                "timestamp", Instant.now(),
                "status", 409,
                "error", "Conflict",
                "message", ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleNotFound(EntityNotFoundException ex) {
        Map<String, Object> body = Map.of(
                "timestamp", Instant.now(),
                "status", 404,
                "error", "Not Found",
                "message", ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(Exception ex) {
        Map<String, Object> body = Map.of(
                "timestamp", Instant.now(),
                "status", 500,
                "error", "Internal Server Error",
                "message", "Ha ocurrido un error inesperado."
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}