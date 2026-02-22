# Overview
- See [ReciMe Coding Challenge Documentation](https://docs.google.com/document/d/1W-gKGRMHhANUi13GGyCquMLQIm0M-gMKcPjTktNS_28/edit?usp=sharing) for:
  - Machine setup instructions
  - Domain model

# Use the Application
## Prepare the environment
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
source env.sh # in case you're on a new shell
./init-recipes.sh
```

## Export the OpenAPI specs to a Postman collection
- While the application is running, visit Swagger UI at `http://localhost:8080/swagger-ui/index.html`
- Under "OpenAPI definition", click `/v3/api-docs`
- Copy the JSON contents and import to Postman

# Design Decisions
- Externalized Configuration
  - Created a file `env.sh` to simulate a deployment environment
- Layered Architecture
  - Classes are distributed betweem `/api`, `/application`, `/domain`, and `/infrastucture` to clearly delineate concerns
  - DTOs are created to transfer data between layers
- Use of TEXT datatype
  - Used TEXT over VARCHAR for faster iteration
- Use of JPA Specifications
  - For dynamic and optional query filters
- Indexing for scaling
  - Added on frequently filtered columns to reduce database latency
- Exception Translation
  - Created a `ControllerExceptionHandler` to map errors to HTTP status codes
  - Created `ApiExceptions` and `DomainExceptions` classes to provide focused error messages
- Parameter objects
  - Used objects as parameters in controller and service methods to improve readability and reduce diff size when updating method signatures
- Test-Driven development
  - Wrote test cases prior to implementation of services to set standards for method behavior early

# Assumptions
- Servings
  - Interpreted as an integer indicating number of people served
- Ingredients
  - Names are unique
- Filtering
  - Ingredients - cannot be both included and excluded
- Vegetarian status
  - Defined at the recipe level, not ingredient level

# Improvement Ideas
- Input validation
  - Character restrictions in input validation (prevent unusual characters)
- Search
  - Customizable servings filter (e.g., filter for "recipes that serve between 1 and 3 people", or "at least 4 people")
  - Use `websearch_to_tsquery` in instructions content search
- Recipes
  - Denormalize `recipe` table by adding a `TEXT[] ingredients` column
- Ingredients
  - Implement quantities
    - Implement units (cup, piece, slice) and pluralization
- Vegetarian status
  - Use as part of a tag system (e.g., Keto, Gluten-free)
- Spring Security
  - Require authentication before granting API access
- Controller
  - Use OpenAPI Generator to enable contract-first development