package com.example.recipes.domain.entity;

import jakarta.persistence.Embeddable;
import java.util.UUID;

@Embeddable
public record RecipeIngredientId(
    UUID recipeId,
    UUID ingredientId
) {
}
