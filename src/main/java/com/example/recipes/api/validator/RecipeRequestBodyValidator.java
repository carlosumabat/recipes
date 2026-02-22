package com.example.recipes.api.validator;

import com.example.recipes.api.dto.RecipeRequestBody;
import com.example.recipes.api.exception.ApiExceptions;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RecipeRequestBodyValidator {

    public static void validate(RecipeRequestBody body) {
        List<String> ingredients = body.ingredients();

        Set<String> seen = new HashSet<>();
        Set<String> duplicates = ingredients.stream()
            .map(String::toLowerCase)
            .filter(i -> !seen.add(i))
            .collect(Collectors.toSet());

        if (!duplicates.isEmpty()) {
            throw new ApiExceptions.BadRequestException(
                "Duplicate ingredients are not allowed: " + duplicates,
                "ingredients",
                ingredients.toString()
            );
        }
    }
}