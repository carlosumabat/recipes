package com.example.recipes.application;

import com.example.recipes.application.dto.ListRecipeCommand;
import com.example.recipes.application.dto.SetRecipeCommand;
import com.example.recipes.domain.entity.Recipe;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface RecipeService {
    Recipe getRecipe(UUID id);

    Recipe createRecipe(SetRecipeCommand command);

    Recipe updateRecipe(SetRecipeCommand command);

    void deleteRecipe(UUID id);

    Page<Recipe> listRecipes(ListRecipeCommand command);
}
