package com.example.recipes.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.recipes.application.RecipeService;
import com.example.recipes.application.shared.DomainExceptions;
import com.example.recipes.domain.entity.Recipe;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

@WebMvcTest(RecipeController.class)
@TestPropertySource(properties = "logging.level.root=OFF")
class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private RecipeService recipeService;

    private Recipe recipeStub;
    private ObjectNode node;

    @BeforeEach
    void setUp() {
        recipeStub = Instancio.create(Recipe.class);
        node = validRecipeNode();
    }

    // =========================================================
    // getRecipe()
    // =========================================================

    @Test
    void Given_RecipeId_When_GetRecipe_Should_Return200() throws Exception {
        when(recipeService.getRecipe(recipeStub.getId()))
            .thenReturn(recipeStub);

        mockMvc.perform(get("/api/recipes/{id}", recipeStub.getId()))
            .andExpect(status().isOk());

        verify(recipeService).getRecipe(recipeStub.getId());
    }

    @Test
    void Given_ResourceNotFoundException_When_GetRecipe_Should_Return404() throws Exception {
        when(recipeService.getRecipe(recipeStub.getId()))
            .thenThrow(DomainExceptions.ResourceNotFoundException.class);

        mockMvc.perform(get("/api/recipes/{id}", recipeStub.getId()))
            .andExpect(status().isNotFound());

        verify(recipeService).getRecipe(recipeStub.getId());
    }

    // =========================================================
    // postRecipe()
    // =========================================================

    @Test
    void Given_ValidRecipe_When_PostRecipe_Should_Return201() throws Exception {
        when(recipeService.createRecipe(any())).thenReturn(recipeStub);

        performPostAndExpect(status().isCreated());

        verify(recipeService).createRecipe(any());
    }

    @Test
    void Given_ResourceNotFoundException_When_PostRecipe_Should_Return404() throws Exception {
        when(recipeService.createRecipe(any()))
            .thenThrow(DomainExceptions.ResourceNotFoundException.class);

        performPostAndExpect(status().isNotFound());

        verify(recipeService).createRecipe(any());
    }

    @Test
    void Given_RecipeWithBlankTitle_When_PostRecipe_Should_Return400() throws Exception {
        node.put("title", "");
        performPostAndExpect(status().isBadRequest());

        node.putNull("title");
        performPostAndExpect(status().isBadRequest());

        verifyNoInteractions(recipeService);
    }

    @Test
    void Given_RecipeWithInvalidTitle_When_PostRecipe_Should_Return400() throws Exception {
        node.put("title", "a");
        performPostAndExpect(status().isBadRequest());

        verifyNoInteractions(recipeService);
    }

    @Test
    void Given_RecipeWithInvalidDescription_When_PostRecipe_Should_Return400() throws Exception {
        node.put("description", "a");
        performPostAndExpect(status().isBadRequest());

        verifyNoInteractions(recipeService);
    }

    @Test
    void Given_RecipeWithInvalidServings_When_PostRecipe_Should_Return400() throws Exception {
        node.put("servings", 0);
        performPostAndExpect(status().isBadRequest());

        node.put("servings", 16);
        performPostAndExpect(status().isBadRequest());

        verifyNoInteractions(recipeService);
    }

    @Test
    void Given_RecipeWithNullIsVegetarian_When_PostRecipe_Should_Return400() throws Exception {
        node.putNull("isVegetarian");
        performPostAndExpect(status().isBadRequest());

        verifyNoInteractions(recipeService);
    }

    @Test
    void Given_RecipeWithBlankInstructions_When_PostRecipe_Should_Return400() throws Exception {
        node.put("instructions", "");
        performPostAndExpect(status().isBadRequest());

        node.putNull("instructions");
        performPostAndExpect(status().isBadRequest());

        verifyNoInteractions(recipeService);
    }

    @Test
    void Given_RecipeWithInvalidInstructions_When_PostRecipe_Should_Return400() throws Exception {
        node.put("instructions", "a");
        performPostAndExpect(status().isBadRequest());

        verifyNoInteractions(recipeService);
    }

    // =========================================================
    // putRecipe()
    // =========================================================

    @Test
    void Given_ValidRecipeAndExistingId_When_PutRecipe_Should_Return200() throws Exception {
        when(recipeService.updateRecipe(any())).thenReturn(recipeStub);

        performPutAndExpect(status().isOk());

        verify(recipeService).updateRecipe(any());
    }

    @Test
    void Given_ResourceNotFoundException_When_PutRecipe_Should_Return404() throws Exception {
        when(recipeService.updateRecipe(any()))
            .thenThrow(DomainExceptions.ResourceNotFoundException.class);

        performPutAndExpect(status().isNotFound());

        verify(recipeService).updateRecipe(any());
    }

    @Test
    void Given_RecipeWithBlankTitle_When_PutRecipe_Should_Return400() throws Exception {
        node.put("title", "");
        performPutAndExpect(status().isBadRequest());

        node.putNull("title");
        performPutAndExpect(status().isBadRequest());

        verifyNoInteractions(recipeService);
    }

    @Test
    void Given_RecipeWithInvalidTitle_When_PutRecipe_Should_Return400() throws Exception {
        node.put("title", "a");
        performPutAndExpect(status().isBadRequest());

        verifyNoInteractions(recipeService);
    }

    @Test
    void Given_RecipeWithInvalidDescription_When_PutRecipe_Should_Return400() throws Exception {
        node.put("description", "a");
        performPutAndExpect(status().isBadRequest());

        verifyNoInteractions(recipeService);
    }

    @Test
    void Given_RecipeWithInvalidServings_When_PutRecipe_Should_Return400() throws Exception {
        node.put("servings", 0);
        performPutAndExpect(status().isBadRequest());

        node.put("servings", 16);
        performPutAndExpect(status().isBadRequest());

        verifyNoInteractions(recipeService);
    }

    @Test
    void Given_RecipeWithNullIsVegetarian_When_PutRecipe_Should_Return400() throws Exception {
        node.putNull("isVegetarian");
        performPutAndExpect(status().isBadRequest());

        verifyNoInteractions(recipeService);
    }

    @Test
    void Given_RecipeWithBlankInstructions_When_PutRecipe_Should_Return400() throws Exception {
        node.put("instructions", "");
        performPutAndExpect(status().isBadRequest());

        node.putNull("instructions");
        performPutAndExpect(status().isBadRequest());

        verifyNoInteractions(recipeService);
    }

    @Test
    void Given_RecipeWithInvalidInstructions_When_PutRecipe_Should_Return400() throws Exception {
        node.put("instructions", "a");
        performPutAndExpect(status().isBadRequest());

        verifyNoInteractions(recipeService);
    }

    // =========================================================
    // deleteRecipe()
    // =========================================================

    @Test
    void Given_RecipeId_When_DeleteRecipe_Should_Return200() throws Exception {
        mockMvc.perform(delete("/api/recipes/{id}", recipeStub.getId().toString()))
            .andExpect(status().isOk());

        verify(recipeService).deleteRecipe(recipeStub.getId());
    }

    @Test
    void Given_ResourceNotFoundException_When_DeleteRecipe_Should_Return404() throws Exception {
        doThrow(DomainExceptions.ResourceNotFoundException.class)
            .when(recipeService).deleteRecipe(recipeStub.getId());

        mockMvc.perform(delete("/api/recipes/{id}", recipeStub.getId()))
            .andExpect(status().isNotFound());

        verify(recipeService).deleteRecipe(recipeStub.getId());
    }

    // =========================================================
    // getRecipeList()
    // =========================================================

    @Test
    void Given_ValidFilterAndSearchQueries_When_GetRecipeList_Should_Return_200() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Recipe> page = new PageImpl<>(List.of(recipeStub), pageable, 1);

        when(recipeService.listRecipes(any())).thenReturn(page);

        mockMvc.perform(get("/api/recipes")
                .param("isVegetarian", "true")
                .param("servings", "2")
                .param("search", "pasta"))
            .andExpect(status().isOk());

        verify(recipeService).listRecipes(any());
    }

    @Test
    void Given_InvalidVegetarianFilter_When_GetRecipeList_Should_Return_400() throws Exception {
        performGetWithParamAndExpectBadRequest("isVegetarian", "invalid");

        verifyNoInteractions(recipeService);
    }

    @Test
    void Given_InvalidServingsFilter_When_GetRecipeList_Should_Return_400() throws Exception {
        performGetWithParamAndExpectBadRequest("servings", "-1");
        performGetWithParamAndExpectBadRequest("servings", "16");

        verifyNoInteractions(recipeService);
    }

    @Test
    void Given_InvalidIngredientsFilter_When_GetRecipeList_Should_Return_400() throws Exception {
//        performGetWithParamAndExpectBadRequest("include", "abc,cde");
        performGetWithParamAndExpectBadRequest("include", "a");
        performGetWithParamAndExpectBadRequest("include", "");
        verifyNoInteractions(recipeService);
    }

    @Test
    void Given_InvalidSearchFilter_When_GetRecipeList_Should_Return_400() throws Exception {
        performGetWithParamAndExpectBadRequest("search", "ab");
        performGetWithParamAndExpectBadRequest("search", StringUtils.repeat("a", 101));
        verifyNoInteractions(recipeService);
    }


    private ObjectNode validRecipeNode() {
        ObjectNode node = objectMapper.createObjectNode();
        node.put("title", "Test Recipe");
        node.put("description", "Description");
        node.put("servings", 2);
        node.put("isVegetarian", true);
        node.put("instructions", "Cook it well.");

        ArrayNode ingredients = objectMapper.createArrayNode();
        ingredients.add("Salt");
        ingredients.add("Pepper");
        node.set("ingredients", ingredients);

        return node;
    }

    private void performGetWithParamAndExpectBadRequest(String name, String value) throws Exception {
        mockMvc.perform(get("/api/recipes")
                .param(name, value))
            .andExpect(status().isBadRequest());
    }

    private void performPostAndExpect(ResultMatcher resultMatcher) throws Exception {
        mockMvc.perform(post("/api/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(node)))
            .andExpect(resultMatcher);
    }

    private void performPutAndExpect(ResultMatcher resultMatcher) throws Exception {
        mockMvc.perform(put("/api/recipes/{id}", recipeStub.getId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(node)))
            .andExpect(resultMatcher);
    }
}