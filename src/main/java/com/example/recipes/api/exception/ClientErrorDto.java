package com.example.recipes.api.exception;

import java.util.List;

public record ClientErrorDto(
    List<ClientError> errors
) {
}
