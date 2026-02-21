package com.example.recipes.domain.entity;

import java.util.UUID;

public record RecipeIngredientId(
    UUID recipeId,
    UUID ingredientId
) {
}
