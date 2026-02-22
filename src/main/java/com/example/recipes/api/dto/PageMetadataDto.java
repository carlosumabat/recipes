package com.example.recipes.api.dto;

public record PageMetadataDto(
    Integer page,
    Integer size,
    Integer total,
    Integer totalPages
) {
}
