package com.example.recipes.api.shared;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ClientErrorDto> handleValidationException(
        MethodArgumentNotValidException ex) {
        List<ClientError> errors = ex.getBindingResult().getFieldErrors().stream()
            .map(fieldError -> new ClientError(fieldError.getField(), fieldError.getDefaultMessage()))
            .toList();

        ClientErrorDto response = new ClientErrorDto(errors);

        errors.forEach(
            err -> log.warn("Field Validation error - field='{}' message='{}'", err.field(), err.message()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
