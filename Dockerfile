# Build-vaihe
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /opt/app

# Kopioi projektin metadata ja lataa riippuvuudet
COPY pom.xml ./pom.xml
RUN mvn -B dependency:go-offline

# Kopioi lähdekoodi
COPY src ./src

# Buildaa projekti
RUN mvn -B clean install -DskipTests

# Kopioi JAR-tiedosto
RUN cp target/*.jar /opt/app/app.jar

# Runtime-vaihe
FROM eclipse-temurin:17-jre-alpine

WORKDIR /opt/app

# Kopioi buildattu JAR-tiedosto
COPY --from=builder /opt/app/app.jar /opt/app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]