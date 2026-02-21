package com.example.recipes.domain.repository;

import com.example.recipes.domain.entity.Ingredient;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {
    @Query(value = "SELECT * FROM ingredient WHERE name::TEXT ILIKE '%' || :name || '%'", nativeQuery = true)
    List<Ingredient> findByNameLike(@Param("name") String name);
}
