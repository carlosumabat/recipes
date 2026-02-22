package com.example.recipes.api.dto;

import java.util.List;

public record RecipeListDto(
    List<RecipeDto> content,
    PageMetadataDto page
) {
}
