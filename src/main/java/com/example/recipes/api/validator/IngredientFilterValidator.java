package com.example.recipes.api.validator;

import com.example.recipes.api.dto.RecipeSearchParams;
import com.example.recipes.api.exception.ApiExceptions;
import java.util.HashSet;
import java.util.Set;
import org.springframework.util.CollectionUtils;

public class IngredientFilterValidator {
    private IngredientFilterValidator() {
    }

    public static void validate(RecipeSearchParams params) {
        Set<String> include = params.include();
        Set<String> exclude = params.exclude();

        if (CollectionUtils.isEmpty(include) || CollectionUtils.isEmpty(exclude)) {
            return;
        }

        Set<String> overlapping = new HashSet<>(include);
        overlapping.retainAll(exclude);

        if (!overlapping.isEmpty()) {
            throw new ApiExceptions.BadRequestException(
                "Ingredients appeared in both include and exclude lists: " + overlapping,
                "include, exclude",
                null
            );
        }
    }
}