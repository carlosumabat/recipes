package com.example.recipes.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.recipes.domain.entity.Ingredient;
import com.example.recipes.domain.repository.IngredientRepository;
import java.util.List;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class IngredientServiceImplTest {
    @Mock
    private IngredientRepository ingredientRepository;

    private IngredientService ingredientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ingredientService = new IngredientServiceImpl(ingredientRepository);

        List<Ingredient> ingredients = Instancio.ofList(Ingredient.class).size(3).create();
        when(ingredientRepository.findByNameLike(any())).thenReturn(ingredients);
    }

    @Test
    void Given_Name_When_SearchIngredients_Should_FindByNameLike() {
        String name = "test";

        ingredientService.searchIngredients(name);

        verify(ingredientRepository).findByNameLike(name);
    }
}