package com.example.recipes.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Set;

public record RecipeSearchParams(

    Boolean isVegetarian,

    @Min(1)
    @Max(15)
    Integer servings,

    @Size(min = 1, max = 20)
    Set<@NotBlank @Size(min = 3, max = 100) String> include,

    @Size(min = 1, max = 20)
    Set<@NotBlank @Size(min = 3, max = 100) String> exclude,

    @Size(min = 3, max = 100)
    String search,

    // Pagination

    @Min(0)
    Integer page,

    @Min(1)
    @Max(100)
    Integer size,

    @Pattern(
        regexp = "title|servings",
        message = "sort must be one of: title, servings"
    )
    String sort,

    @Pattern(
        regexp = "asc|desc",
        flags = Pattern.Flag.CASE_INSENSITIVE,
        message = "order must be 'asc' or 'desc'"
    )
    String order
) {
}
