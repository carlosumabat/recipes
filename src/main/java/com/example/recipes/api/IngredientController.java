package com.example.recipes.api;

import com.example.recipes.api.dto.IngredientLookupDto;
import com.example.recipes.api.dto.IngredientSearchParams;
import com.example.recipes.application.IngredientService;
import com.example.recipes.domain.entity.Ingredient;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Ingredients")
@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
@Validated
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping("/search")
    public ResponseEntity<IngredientLookupDto> getIngredients(@Valid @ModelAttribute IngredientSearchParams params) {
        List<Ingredient> ingredients = ingredientService.searchIngredients(params.name());

        IngredientLookupDto dto = new IngredientLookupDto(ingredients.stream().map(Ingredient::getName).toList());
        return ResponseEntity.ok(dto);
    }
}
