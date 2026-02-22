package com.example.recipes.application.shared;

public class DomainExceptions {
    private DomainExceptions() {
    }

    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
