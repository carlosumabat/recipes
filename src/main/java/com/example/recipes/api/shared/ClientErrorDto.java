package com.example.recipes.api.shared;

import java.util.List;

public record ClientErrorDto(
    List<ClientError> errors
) {
}
