export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5433/recipes
export SPRING_DATASOURCE_USERNAME=user
export SPRING_DATASOURCE_PASSWORD=pw
export SPRING_APPLICATION_JSON='{
  "spring.jpa.show-sql":true,
  "logging.level.org.hibernate.SQL":"debug",
  "logging.level.org.hibernate.orm.jdbc.bind":"trace",
  "spring.jpa.properties.hibernate.format_sql":true
}'

export SPRING_OUTPUT_ANSI_ENABLED=ALWAYS
