package com.example.recipes.api.dto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record RecipeDto(
    UUID id,
    String title,
    String description,
    Integer servings,
    boolean isVegetarian,
    String instructions,
    List<String> ingredients,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt
) {
}
