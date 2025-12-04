package project.hh.ticketguru;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class AuthE2eTest extends BaseE2eTest {

  // Vaihda polku suojattuun GET-endpointtiin, joka vaatii tunnukset.
  private static final String PROTECTED_PATH = "/api/events";

  @Test
  @DisplayName("Ilman tunnuksia suojattu endpoint antaa 401/403")
  void requiresAuth() {
    given()
      .accept(ContentType.JSON)
    .when()
      .get(PROTECTED_PATH)
    .then()
      .statusCode(anyOf(is(401), is(403)));
  }

  @Test
  @DisplayName("Oikeilla tunnuksilla suojattu endpoint toimii")
  void withValidCreds() {
    given()
      .auth().preemptive().basic(USERNAME, PASSWORD)
      .accept(ContentType.JSON)
    .when()
      .get(PROTECTED_PATH)
    .then()
      .statusCode(anyOf(is(200), is(204)));
  }

  @Test
  @DisplayName("Väärillä tunnuksilla tulee 401/403")
  void withWrongCreds() {
    given()
      .auth().preemptive().basic("wrong", "wrong")
      .accept(ContentType.JSON)
    .when()
      .get(PROTECTED_PATH)
    .then()
      .statusCode(anyOf(is(401), is(403)));
  }
}
