package com.example.recipes.api;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.recipes.application.IngredientService;
import com.example.recipes.domain.entity.Ingredient;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(IngredientController.class)
class IngredientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IngredientService ingredientService;

    @Test
    void Given_SearchQuery_When_SearchIngredients_Should_Return200() throws Exception {
        List<Ingredient> ingredients = List.of(
            new Ingredient("Sugar"),
            new Ingredient("Salt")
        );

        when(ingredientService.searchIngredients(anyString())).thenReturn(ingredients);

        mockMvc.perform(get("/api/ingredients/search")
                .param("name", "abc"))
            .andExpect(status().isOk())
            .andExpect(content().json("""
                    {"data": ["Sugar","Salt"]}
                """));

        verify(ingredientService).searchIngredients(anyString());
    }

    @Test
    void Given_BlankNameParam_When_SearchIngredients_Should_Return400() throws Exception {
        mockMvc.perform(get("/api/ingredients/search")
                .param("name", ""))
            .andExpect(status().isBadRequest());

        mockMvc.perform(get("/api/ingredients/search"))
            .andExpect(status().isBadRequest());

        verifyNoInteractions(ingredientService);
    }

    @Test
    void Given_InvalidSizeNameParam_When_SearchIngredients_Should_Return400() throws Exception {
        mockMvc.perform(get("/api/ingredients/search")
                .param("name", "su"))
            .andExpect(status().isBadRequest());

        verifyNoInteractions(ingredientService);
    }

}