package com.example.recipes.api.exception;

import lombok.Getter;

public class ApiExceptions {

    private ApiExceptions() {
    }

    public static class NotFoundException extends RuntimeException {
        public NotFoundException(String message) {
            super(message);
        }
    }


    @Getter
    public static class BadRequestException extends RuntimeException {
        private final String field;
        private final String value;

        public BadRequestException(String message, String field, String value) {
            super(message);
            this.field = field;
            this.value = value;
        }
    }
}
