# Use the Application
## First, prepare the environment

- Start up Docker containers
```bash
docker compose up -d
```

- If you're on a new shell, set environment variables
```bash
source env.sh
```

- Install dependencies and run tests
```bash
./mvnw clean install
```

- Run database migrations
```bash
./mvnw liquibase:update \
-Dliquibase.url="$SPRING_DATASOURCE_URL" \
-Dliquibase.username="$SPRING_DATASOURCE_USERNAME" \
-Dliquibase.password="$SPRING_DATASOURCE_PASSWORD" \
-Dliquibase.changeLogFile=src/main/resources/db/db.changelog-master.yaml
```

- Populate the database
```bash
cat src/main/resources/db/init/init_ingredients.sql \
| docker exec -i recipes-postgresql-1 psql -U user -d recipes
```

## Run the application
```bash
./mvnw spring-boot:run
```

- To populate the `recipe` table for the first time, run:
```bash
./init-recipes.sh
```

# Export the API to a Postman Collection


# Design Decisions
- Externalized Configuration - 
- Layered Architecture
- Use of TEXT datatype
- Use of JPA Specifications
- Indexing for scaling
- Exception handler
- Parameter object for Controller
- TDD

# Assumptions
- Servings
  - Integer showing number of people served, not yield
- Ingredients
  - Names are unique
- Filtering
  - Different filters joined by AND condition
  - Ingredient cannot be both included and excluded
- Vegetarian
  - Recipe-level, not ingredient-level

# Improvement Ideas
- Input validation
  - Character restrictions
- Search
  - Ranged search for servings
  - Use websearch_to_tsquery in instructions search
- Recipes
  - Remove `recipe_ingredient` table
- Ingredients
  - Use cursor-based pagination and implement partial search with trigram index
  - Implement pluralization in DTO
  - Implement units (cup, piece, slice)
- Vegetarian status
  - Use as part of a tag system
- Spring Security
- Controller
  - Use OpenAPI generator
- Tests for pagination