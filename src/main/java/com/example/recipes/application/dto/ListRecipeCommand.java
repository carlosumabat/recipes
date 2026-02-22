package com.example.recipes.application.dto;

import java.util.Set;

public record ListRecipeCommand(
    Boolean isVegetarian,
    Integer servings,
    Set<String> includeIngredients,
    Set<String> excludeIngredients,
    String search,

    Integer page,
    Integer size,
    String sort,
    String order
) {
}