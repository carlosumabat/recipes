package com.example.recipes.api.shared;

public record ClientError(
    String field,
    String message
) {
}
