package com.example.recipes.application;

import com.example.recipes.application.dto.ListRecipeCommand;
import com.example.recipes.application.dto.SetRecipeCommand;
import com.example.recipes.application.shared.DomainExceptions;
import com.example.recipes.domain.entity.Ingredient;
import com.example.recipes.domain.entity.Recipe;
import com.example.recipes.domain.entity.RecipeIngredient;
import com.example.recipes.domain.entity.RecipeIngredientId;
import com.example.recipes.domain.repository.RecipeRepository;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientService ingredientService;

    public Recipe getRecipe(UUID id) {
        return retrieveRecipe(id);
    }

    public Recipe createRecipe(SetRecipeCommand command) {
        Recipe recipe = new Recipe();
        List<Ingredient> ingredients = ingredientService.getIngredients(command.ingredients());

        validateNoUnknownIngredients(ingredients, command.ingredients());
        setRecipeFields(recipe, command);
        recipeRepository.save(recipe);
        attachIngredients(recipe, ingredients);

        return recipeRepository.save(recipe);
    }

    public Recipe updateRecipe(SetRecipeCommand command) {
        Recipe recipe = retrieveRecipe(command.id());
        List<Ingredient> ingredients = ingredientService.getIngredients(command.ingredients());

        validateNoUnknownIngredients(ingredients, command.ingredients());
        setRecipeFields(recipe, command);
        attachIngredients(recipe, ingredients);

        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(UUID id) {
        recipeRepository.delete(retrieveRecipe(id));
    }

    public Page<Recipe> listRecipes(ListRecipeCommand command) {
        Pageable pageable = createPageable(command);
        Specification<Recipe> spec = RecipeSpecificationBuilder.buildSpecification(command);
        return recipeRepository.findAll(spec, pageable);
    }


    private void setRecipeFields(Recipe recipe, SetRecipeCommand command) {
        recipe.setTitle(command.title());
        recipe.setDescription(command.description());
        recipe.setServings(command.servings());
        recipe.setIsVegetarian(command.isVegetarian());
        recipe.setInstructions(command.instructions());

    }

    private void attachIngredients(Recipe recipe, List<Ingredient> ingredients) {
        recipe.getRecipeIngredients().clear();

        for (Ingredient ingredient : ingredients) {
            RecipeIngredientId recipeIngredientId = new RecipeIngredientId(recipe.getId(), ingredient.getId());
            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setId(recipeIngredientId);
            recipeIngredient.setRecipe(recipe);
            recipeIngredient.setIngredient(ingredient);
            recipeIngredient.setQuantity("Not implemented");
            recipe.getRecipeIngredients().add(recipeIngredient);
        }
    }

    private Recipe retrieveRecipe(UUID id) {
        return recipeRepository.findById(id).orElseThrow(
            () -> new DomainExceptions.ResourceNotFoundException("Recipe not found with ID: " + id)
        );
    }

    private void validateNoUnknownIngredients(List<Ingredient> records, List<String> input) {
        List<String> unknownIngredients = findUnknownIngredients(records, input);

        if (!unknownIngredients.isEmpty()) {
            throw new DomainExceptions.ResourceNotFoundException(
                "One or more ingredients not found: " + unknownIngredients);
        }
    }

    private List<String> findUnknownIngredients(List<Ingredient> records, List<String> input) {
        if (records.isEmpty()) {
            return input;
        }

        Set<String> recordSet = records.stream()
            .map(Ingredient::getName)
            .map(i -> i.trim().toLowerCase())
            .collect(Collectors.toSet());

        return input.stream()
            .filter(i -> !recordSet.contains(i.trim().toLowerCase()))
            .toList();
    }

    private Pageable createPageable(ListRecipeCommand command) {
        int page = command.page() == null ? 0 : command.page();
        int size = command.size() == null ? 25 : command.size();
        String sort = command.sort() == null ? "createdAt" : command.sort();
        Sort.Direction order = "desc".equalsIgnoreCase(command.order()) ? Sort.Direction.DESC : Sort.Direction.ASC;

        return PageRequest.of(page, size, Sort.by(order, sort));
    }

}
