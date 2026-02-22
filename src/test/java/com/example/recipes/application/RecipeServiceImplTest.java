package com.example.recipes.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.instancio.Select.field;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.recipes.application.dto.ListRecipeCommand;
import com.example.recipes.application.dto.SetRecipeCommand;
import com.example.recipes.application.shared.DomainExceptions;
import com.example.recipes.domain.entity.Ingredient;
import com.example.recipes.domain.entity.Recipe;
import com.example.recipes.domain.repository.RecipeRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

class RecipeServiceImplTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private IngredientService ingredientService;

    private RecipeService recipeService;

    private Recipe recipeStub;
    private Ingredient ingredientStub;
    private SetRecipeCommand setRecipeCommandStub;
    private ListRecipeCommand listRecipeCommandStub;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, ingredientService);

        recipeStub = Instancio.create(Recipe.class);
        ingredientStub = Instancio.create(Ingredient.class);

        listRecipeCommandStub = Instancio.create(ListRecipeCommand.class);

        setRecipeCommandStub = Instancio.of(SetRecipeCommand.class)
            .set(field(SetRecipeCommand::id), recipeStub.getId())
            .set(field(SetRecipeCommand::ingredients), List.of(ingredientStub.getName()))
            .create();

        when(recipeRepository.findById(recipeStub.getId())).thenReturn(Optional.of(recipeStub));
        when(recipeRepository.save(any(Recipe.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        when(ingredientService.getIngredients(any())).thenReturn(List.of(ingredientStub));
    }

    @Test
    void Given_RecipeId_When_GetRecipe_Should_ReturnRecipe() {
        Recipe result = recipeService.getRecipe(recipeStub.getId());

        assertThat(result).isEqualTo(recipeStub);
        verify(recipeRepository).findById(recipeStub.getId());
    }

    @Test
    void Given_NonexistentRecipeId_When_GetRecipe_Should_ThrowNotFound() {
        when(recipeRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> recipeService.getRecipe(UUID.randomUUID()))
            .isInstanceOf(DomainExceptions.ResourceNotFoundException.class);

        verify(recipeRepository).findById(any());
    }

    @Test
    void Given_ValidSetRecipeCommand_When_CreateRecipe_Should_ReturnNewRecipe() {
        Recipe result = recipeService.createRecipe(setRecipeCommandStub);

        assertRecipeEqualsSetCommand(result);
        verify(recipeRepository, times(2)).save(any(Recipe.class));
    }

    @Test
    void Given_ValidSetRecipeCommandAndNonexistentIngredient_When_CreateRecipe_Should_ThrowNotFound() {
        when(ingredientService.getIngredients(any())).thenReturn(Collections.emptyList());

        assertThatThrownBy(() -> recipeService.createRecipe(setRecipeCommandStub))
            .isInstanceOf(DomainExceptions.ResourceNotFoundException.class);

        verify(recipeRepository, never()).save(recipeStub);
    }

    @Test
    void Given_ValidSetRecipeCommand_When_UpdateRecipe_Should_ReturnUpdatedRecipe() {
        Recipe result = recipeService.updateRecipe(setRecipeCommandStub);

        assertRecipeEqualsSetCommand(result);
        verify(recipeRepository).findById(setRecipeCommandStub.id());
        verify(recipeRepository).save(recipeStub);
    }

    @Test
    void Given_ValidSetRecipeCommandWithNullDescriptionOrServings_When_UpdateRecipe_Should_SetFieldsToNull() {
        setRecipeCommandStub = Instancio.of(SetRecipeCommand.class)
            .set(field(SetRecipeCommand::id), recipeStub.getId())
            .set(field(SetRecipeCommand::ingredients), List.of(ingredientStub.getName()))
            .ignore(field(SetRecipeCommand::description))
            .ignore(field(SetRecipeCommand::servings))
            .create();

        Recipe result = recipeService.updateRecipe(setRecipeCommandStub);

        assertRecipeEqualsSetCommand(result);
        verify(recipeRepository).findById(setRecipeCommandStub.id());
        verify(recipeRepository).save(recipeStub);
    }

    @Test
    void Given_NonexistentRecipeId_When_UpdateRecipe_Should_ThrowNotFound() {
        when(recipeRepository.findById(recipeStub.getId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> recipeService.updateRecipe(setRecipeCommandStub))
            .isInstanceOf(DomainExceptions.ResourceNotFoundException.class);

        verify(recipeRepository, never()).save(recipeStub);
    }

    @Test
    void Given_ValidSetRecipeCommandAndNonexistentIngredient_When_UpdateRecipe_Should_ThrowNotFound() {
        when(ingredientService.getIngredients(any())).thenReturn(Collections.emptyList());

        assertThatThrownBy(() -> recipeService.updateRecipe(setRecipeCommandStub))
            .isInstanceOf(DomainExceptions.ResourceNotFoundException.class);

        verify(recipeRepository, never()).save(any());
    }

    @Test
    void Given_RecipeId_When_DeleteRecipe_Should_Succeed() {
        assertThatNoException().isThrownBy(() -> recipeService.deleteRecipe(recipeStub.getId()));

        verify(recipeRepository).delete(recipeStub);
    }

    @Test
    void Given_NonexistentRecipeId_When_DeleteRecipe_Should_ThrowNotFound() {
        when(recipeRepository.findById(recipeStub.getId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> recipeService.deleteRecipe(any()))
            .isInstanceOf(DomainExceptions.ResourceNotFoundException.class);

        verify(recipeRepository, never()).delete(recipeStub);
    }

    @Test
    void Given_ValidGetRecipeCommand_When_ListRecipes_Should_CreateJpaSpecifications() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Recipe> page = new PageImpl<>(List.of(recipeStub), pageable, 1);

        when(recipeRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);

        Page<Recipe> result = recipeService.listRecipes(listRecipeCommandStub);

        assertThat(result.getTotalElements()).isEqualTo(1);

        verify(recipeRepository).findAll(any(Specification.class), any(Pageable.class));
    }

    private void assertRecipeEqualsSetCommand(Recipe result) {
        assertThat(result.getTitle()).isEqualTo(setRecipeCommandStub.title());
        assertThat(result.getDescription()).isEqualTo(setRecipeCommandStub.description());
        assertThat(result.getServings()).isEqualTo(setRecipeCommandStub.servings());
        assertThat(result.getIsVegetarian()).isEqualTo(setRecipeCommandStub.isVegetarian());
        assertThat(result.getInstructions()).isEqualTo(setRecipeCommandStub.instructions());
    }

}