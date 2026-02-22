package com.example.recipes.api;

import com.example.recipes.api.dto.PageMetadataDto;
import com.example.recipes.api.dto.RecipeDto;
import com.example.recipes.api.dto.RecipeListDto;
import com.example.recipes.api.dto.RecipeRequestBody;
import com.example.recipes.api.dto.RecipeSearchParams;
import com.example.recipes.api.exception.ApiExceptions;
import com.example.recipes.api.validator.IngredientFilterValidator;
import com.example.recipes.api.validator.RecipeRequestBodyValidator;
import com.example.recipes.application.RecipeService;
import com.example.recipes.application.dto.ListRecipeCommand;
import com.example.recipes.application.dto.SetRecipeCommand;
import com.example.recipes.application.shared.DomainExceptions;
import com.example.recipes.domain.entity.Recipe;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Recipes")
@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
@Validated
public class RecipeController {
    private static final ZoneId ZONE_ID = ZoneId.of("Asia/Manila");
    private static final String UUID_REGEX =
        "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";

    private final RecipeService recipeService;

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getRecipe(@PathVariable @NotBlank @Pattern(regexp = UUID_REGEX) String id) {
        try {
            Recipe recipe = recipeService.getRecipe(UUID.fromString(id));
            return ResponseEntity.ok(toDto(recipe));
        } catch (DomainExceptions.ResourceNotFoundException ex) {
            throw new ApiExceptions.NotFoundException(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<RecipeDto> postRecipe(@Valid @RequestBody RecipeRequestBody body) {
        RecipeRequestBodyValidator.validate(body);
        try {
            Recipe recipe = recipeService.createRecipe(toSetRecipeCommand(body));
            return ResponseEntity.status(HttpStatus.CREATED).body(toDto(recipe));
        } catch (DomainExceptions.ResourceNotFoundException ex) {
            throw new ApiExceptions.NotFoundException(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeDto> putRecipe(
        @PathVariable @NotBlank @Pattern(regexp = UUID_REGEX) String id,
        @Valid @RequestBody RecipeRequestBody body) {

        RecipeRequestBodyValidator.validate(body);
        try {
            SetRecipeCommand cmd = toSetRecipeCommand(body, UUID.fromString(id));
            Recipe updated = recipeService.updateRecipe(cmd);
            return ResponseEntity.ok(toDto(updated));
        } catch (DomainExceptions.ResourceNotFoundException ex) {
            throw new ApiExceptions.NotFoundException(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable @NotBlank @Pattern(regexp = UUID_REGEX) String id) {
        try {
            recipeService.deleteRecipe(UUID.fromString(id));
            return ResponseEntity.ok().build();
        } catch (DomainExceptions.ResourceNotFoundException ex) {
            throw new ApiExceptions.NotFoundException(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<RecipeListDto> listRecipes(@Valid RecipeSearchParams params) {
        IngredientFilterValidator.validate(params);

        Page<Recipe> recipes = recipeService.listRecipes(toListRecipeCommand(params));
        RecipeListDto dto = new RecipeListDto(
            recipes.getContent().stream().map(this::toNoIngredientsDto).toList(),
            toPageMetadataDto(recipes)
        );
        return ResponseEntity.ok(dto);
    }

    private SetRecipeCommand toSetRecipeCommand(RecipeRequestBody body, UUID id) {
        return new SetRecipeCommand(
            id,
            body.title(),
            body.description(),
            body.servings(),
            body.isVegetarian(),
            body.instructions(),
            body.ingredients()
        );
    }

    private SetRecipeCommand toSetRecipeCommand(RecipeRequestBody body) {
        return toSetRecipeCommand(body, null);
    }

    private ListRecipeCommand toListRecipeCommand(RecipeSearchParams params) {
        return new ListRecipeCommand(
            params.isVegetarian(),
            params.servings(),
            params.include() != null ? params.include() : Set.of(),
            params.exclude() != null ? params.exclude() : Set.of(),
            params.search(),
            params.page(),
            params.size(),
            params.sort(),
            params.order()
        );
    }

    private RecipeDto toDto(Recipe recipe) {
        return new RecipeDto(
            recipe.getId(),
            recipe.getTitle(),
            recipe.getDescription(),
            recipe.getServings(),
            recipe.getIsVegetarian(),
            recipe.getInstructions(),
            recipe.getRecipeIngredients().stream()
                .map(ri -> ri.getIngredient().getName())
                .toList(),
            OffsetDateTime.ofInstant(recipe.getCreatedAt(), ZONE_ID),
            OffsetDateTime.ofInstant(recipe.getUpdatedAt(), ZONE_ID)
        );
    }

    private RecipeDto toNoIngredientsDto(Recipe recipe) {
        return new RecipeDto(
            recipe.getId(),
            recipe.getTitle(),
            recipe.getDescription(),
            recipe.getServings(),
            recipe.getIsVegetarian(),
            recipe.getInstructions(),
            null,
            OffsetDateTime.ofInstant(recipe.getCreatedAt(), ZONE_ID),
            OffsetDateTime.ofInstant(recipe.getUpdatedAt(), ZONE_ID)
        );
    }

    private PageMetadataDto toPageMetadataDto(Page<?> page) {
        return new PageMetadataDto(
            page.getNumber(),
            page.getSize(),
            (int) page.getTotalElements(),
            page.getTotalPages()
        );
    }
}