package com.example.recipes.application;

import com.example.recipes.domain.entity.Ingredient;
import com.example.recipes.domain.repository.IngredientRepository;
import java.util.HashSet;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    public List<Ingredient> searchIngredients(String name) {
        return ingredientRepository.findByNameLike(name);
    }

    public List<Ingredient> getIngredients(List<String> names) {
        return ingredientRepository.findByNameIn(new HashSet<>(names));
    }
}
