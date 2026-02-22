package com.example.recipes.api.exception;

public record ClientError(
    String field,
    String value,
    String message
) {
}
