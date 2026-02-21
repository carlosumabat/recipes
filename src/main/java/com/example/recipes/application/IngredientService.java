package com.example.recipes.application;

import com.example.recipes.domain.entity.Ingredient;
import java.util.List;

public interface IngredientService {
    List<Ingredient> searchIngredients(String name);
}
