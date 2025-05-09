FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/AssignmentTest-1.0-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
