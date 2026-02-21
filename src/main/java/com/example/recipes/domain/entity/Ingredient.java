package com.example.recipes.domain.entity;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLCITextType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Getter
@Setter
@Entity
@Table(name = "ingredient")
@NoArgsConstructor
public class Ingredient {

    public Ingredient(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Type(PostgreSQLCITextType.class)
    @Column(name = "name", columnDefinition = "citext", nullable = false, unique = true)
    private String name;
}
