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

- Install dependencies
```bash
./mvnw clean install
```

- Run database migrations
```bash
liquibase \
  --classpath=src/main/resources \
  --changelog-file=src/main/resources/db.changelog/db.changelog-master.yaml \
  --url="$SPRING_DATASOURCE_URL" \
  --username="$SPRING_DATASOURCE_USERNAME" \
  --password="$SPRING_DATASOURCE_PASSWORD" \
  update
```

## Then, run the application!
```bash
./mvnw spring-boot:run
```

# Export the API to a Postman Collection


# Design Decisions
- Externalized Configuration - 
- OpenAPI Generator
- Layered Architecture