package com.example.recipes.api;

import com.example.recipes.api.dto.IngredientSearchParams;
import com.example.recipes.application.IngredientService;
import com.example.recipes.domain.entity.Ingredient;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
@Validated
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping("/search")
    public ResponseEntity<List<String>> searchIngredients(@Valid @ModelAttribute IngredientSearchParams params) {
        List<Ingredient> ingredients = ingredientService.searchIngredients(params.name());
        return ResponseEntity.ok(ingredients.stream().map(Ingredient::getName).toList());
    }
}
