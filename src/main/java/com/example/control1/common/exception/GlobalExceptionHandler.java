package com.example.control1.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorModel> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {

        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String name = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.add(name + ": " + message);
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorModel(HttpStatus.BAD_REQUEST.value(), errors));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorModel> handleInvalidDateException(ResponseStatusException ex, WebRequest request) {
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(new ErrorModel(ex.getStatusCode().value(), List.of(Objects.requireNonNull(ex.getReason()))));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorModel> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) throws IOException {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorModel(HttpStatus.BAD_REQUEST.value(), List.of("Invalid enum value")));
    }
}
