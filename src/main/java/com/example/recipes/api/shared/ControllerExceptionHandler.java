package com.example.recipes.api.shared;

import java.util.Collections;
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
    public ResponseEntity<ClientErrorDto> handleValidationException(MethodArgumentNotValidException ex) {
        List<ClientError> errors = ex.getBindingResult().getFieldErrors().stream()
            .map(fieldError -> new ClientError(
                fieldError.getField(),
                fieldError.getRejectedValue() != null ? fieldError.getRejectedValue().toString() : "",
                fieldError.getDefaultMessage()))
            .toList();
        ClientErrorDto response = new ClientErrorDto(errors);

        errors.forEach(
            err -> log.warn("Field Validation error: field='{}', value='{}', message='{}'",
                err.field(), err.value(), err.message()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ApiExceptions.BadRequestException.class)
    public ResponseEntity<ClientErrorDto> handleBadRequestException(ApiExceptions.BadRequestException ex) {
        ClientError error = new ClientError(ex.getField(), ex.getValue(), ex.getMessage());
        ClientErrorDto response = new ClientErrorDto(Collections.singletonList(error));

        log.warn("Bad Request: field='{}', value='{}', message='{}'", error.field(), error.value(), error.message());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ApiExceptions.NotFoundException.class)
    public ResponseEntity<ClientErrorDto> handleNotFoundException(ApiExceptions.NotFoundException ex) {
        ClientError error = new ClientError(null, null, ex.getMessage());
        ClientErrorDto response = new ClientErrorDto(Collections.singletonList(error));

        log.warn("Resource not found: message='{}'", error.message());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
