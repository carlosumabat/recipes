package com.example.recipes.application.dto;

import java.util.List;
import java.util.UUID;

public record SetRecipeCommand(
    UUID id,
    String title,
    String description,
    Integer servings,
    Boolean isVegetarian,
    String instructions,
    List<String> ingredients
) {
}

