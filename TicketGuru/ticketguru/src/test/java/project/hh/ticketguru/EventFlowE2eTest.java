package project.hh.ticketguru;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class EventFlowE2eTest extends BaseE2eTest {

  // Säädä polut teidän rajapinnan mukaan.
  private static final String EVENTS = "/api/events";

  @Test
  @DisplayName("Kirjautunut käyttäjä näkee tapahtumat (GET /api/events)")
  void listEvents() {
    given()
      .auth().preemptive().basic(USERNAME, PASSWORD)
      .accept(ContentType.JSON)
    .when()
      .get(EVENTS)
    .then()
      .statusCode(200)
      .contentType(containsString("json"));
  }
}
