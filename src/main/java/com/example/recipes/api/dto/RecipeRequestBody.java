package com.example.recipes.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record RecipeRequestBody(
    @NotBlank
    @Size(min = 3, max = 120)
    String title,

    @Size(min = 3, max = 500)
    String description,

    @Min(1)
    @Max(15)
    Integer servings,

    @NotNull
    Boolean isVegetarian,

    @NotBlank
    @Size(min = 3, max = 5000)
    String instructions,

    @NotEmpty
    List<@NotBlank @Size(min = 3, max = 20) String> ingredients
) {
}
