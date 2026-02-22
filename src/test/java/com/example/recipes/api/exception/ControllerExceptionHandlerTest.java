package com.example.recipes.api.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RequiredArgsConstructor
class ControllerExceptionHandlerTest {

    private ControllerExceptionHandler handler;

    @BeforeEach
    void setup() {
        handler = new ControllerExceptionHandler();
    }

    @Test
    void Given_Request_When_MethodArgumentNotValid_Should_Handle() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class, RETURNS_DEEP_STUBS);
        FieldError fieldError = new FieldError("userForm", "email", "Email is invalid");

        when(ex.getBindingResult().getFieldErrors()).thenReturn(List.of(fieldError));

        ResponseEntity<ClientErrorDto> response = handler.handleValidationException(ex);
        ClientErrorDto body = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(body).isNotNull();
        assertThat(body.errors()).hasSize(1);
        assertThat(body.errors().get(0).field()).isEqualTo("email");
    }

}