package com.example.recipes.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record IngredientSearchParams(
    @NotBlank
    @Size(min = 3, max = 100)
    String name
) {
}
