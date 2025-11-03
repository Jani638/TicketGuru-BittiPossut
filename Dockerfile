# Build-vaihe
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /opt/app

# Kopioi projektin metadata ja lataa riippuvuudet
COPY TicketGuru/ticketguru/pom.xml ./pom.xml
RUN mvn -B dependency:go-offline

# Kopioi lähdekoodi
COPY TicketGuru/ticketguru/src ./src

# Buildaa projekti
RUN mvn -B clean install -DskipTests

# Kopioi JAR-tiedosto
RUN cp target/*.jar /opt/app/app.jar

# Runtime-vaihe
FROM eclipse-temurin:17-jre-alpine

WORKDIR /opt/app

# Kopioi buildattu JAR-tiedosto
COPY --from=builder /opt/app/app.jar /opt/app/app.jar

# Määritetään oletusarvot ympäristömuuttujille (voi ylikirjoittaa Compose-tasolla)
ENV POSTGRESQL_SERVICE_HOST=localhost \
    POSTGRESQL_SERVICE_PORT=5432 \
    DB_NAME=ticketgurudatabase \
    DB_USER=user20H \
    DB_PASSWORD=0tB4ultSbDnSy5bu

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]