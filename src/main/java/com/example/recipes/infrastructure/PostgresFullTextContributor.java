package com.example.recipes.infrastructure;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.boot.model.FunctionContributor;
import org.hibernate.query.sqm.function.SqmFunctionRegistry;
import org.hibernate.type.StandardBasicTypes;

/*
* Enables use of @@ operator in JPA Specifications for full-text search.
*
* Sources:
* - Spring Data JPA with Full-Text Search - https://gist.github.com/Fabricio20/83c86ccf055c8efc359463dc8a1e895c#postgresql-implementation
* - Registering a FunctionContributor - https://www.linkedin.com/posts/lcregis_how-to-usefunctioncontributorin-hibernate-activity-7357206067426082816-LqA-
*
* The idea to register a new function came from the Github source, but the LinkedIn source
* provided a solution that works for Hibernate 6.
 */

public class PostgresFullTextContributor implements FunctionContributor {

    @Override
    public void contributeFunctions(FunctionContributions contributions) {
        SqmFunctionRegistry registry = contributions.getFunctionRegistry();

        registry.registerPattern(
            "fts",
            "?1 @@ plainto_tsquery('english', ?2)",
            contributions.getTypeConfiguration()
                .getBasicTypeRegistry()
                .resolve(StandardBasicTypes.BOOLEAN)
        );
    }
}
