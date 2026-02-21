package com.example.recipes.application;

import com.example.recipes.domain.entity.Ingredient;
import com.example.recipes.domain.repository.IngredientRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    @Transactional
    public List<Ingredient> searchIngredients(String name) {
        return ingredientRepository.findByNameLike(name);
    }
}
