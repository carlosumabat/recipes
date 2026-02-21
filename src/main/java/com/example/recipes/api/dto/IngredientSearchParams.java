package com.example.recipes.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record IngredientSearchParams(
    @NotBlank
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    String name
) {
}
