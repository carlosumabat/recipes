package com.example.recipes.application;

import com.example.recipes.application.dto.ListRecipeCommand;
import com.example.recipes.domain.entity.Ingredient;
import com.example.recipes.domain.entity.Recipe;
import com.example.recipes.domain.entity.RecipeIngredient;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

public class RecipeSpecificationBuilder {
    public static Specification<Recipe> buildSpecification(ListRecipeCommand command) {
        List<Specification<Recipe>> specifications = Stream.of(
            isVegetarian(command.isVegetarian()),
            servingCount(command.servings()),
            includeIngredients(command.includeIngredients()),
            excludeIngredients(command.excludeIngredients()),
            search(command.search())
        ).toList();

        return Specification.allOf(specifications);
    }

    private static Specification<Recipe> isVegetarian(Boolean isVegetarian) {
        if (isVegetarian == null) {
            return null;
        }
        return (root, query, cb) -> cb.equal(root.get("isVegetarian"), isVegetarian);
    }

    private static Specification<Recipe> servingCount(Integer servings) {
        if (servings == null) {
            return null;
        }
        return (root, query, cb) -> cb.equal(root.get("servings"), servings);
    }

    private static Specification<Recipe> includeIngredients(Set<String> ingredients) {
        if (CollectionUtils.isEmpty(ingredients)) {
            return null;
        }
        return (root, query, cb) -> {
            Subquery<UUID> subquery = query.subquery(UUID.class);
            Root<RecipeIngredient> ri = subquery.from(RecipeIngredient.class);
            Join<RecipeIngredient, Ingredient> ingredient = ri.join("ingredient");

            subquery.select(ri.get("recipe").get("id"))
                .where(ingredient.get("name").in(ingredients))
                .groupBy(ri.get("recipe").get("id"))
                .having(cb.equal(cb.count(ingredient.get("name")), ingredients.size()));

            return root.get("id").in(subquery);
        };
    }

    private static Specification<Recipe> excludeIngredients(Set<String> ingredients) {
        if (CollectionUtils.isEmpty(ingredients)) {
            return null;
        }

        return (root, query, cb) -> {
            Subquery<Integer> subquery = query.subquery(Integer.class);
            Root<RecipeIngredient> ri = subquery.from(RecipeIngredient.class);
            Join<RecipeIngredient, Ingredient> ingredient = ri.join("ingredient");

            subquery.select(cb.literal(1))
                .where(
                    cb.equal(ri.get("recipe").get("id"), root.get("id")),
                    ingredient.get("name").in(ingredients)
                );

            return cb.not(cb.exists(subquery));
        };
    }

    private static Specification<Recipe> search(String search) {
        if (StringUtils.isBlank(search)) {
            return null;
        }

        return (root, query, cb) -> {
            Expression<Boolean> ftsPredicate = cb.function(
                "fts", Boolean.class,
                root.get("searchVector"),
                cb.literal(search)
            );

            return cb.isTrue(ftsPredicate);
        };
    }
}
