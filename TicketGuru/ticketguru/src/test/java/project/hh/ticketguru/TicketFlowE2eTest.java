package project.hh.ticketguru;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TicketFlowE2eTest extends BaseE2eTest {

  // Säädä polut teidän rajapintaan. Jos teillä ei ole näitä, vaihda vastaaviin.
  private static final String EVENTS = "/api/events";

  @Test
  @DisplayName("Käyttäjän peruspolku: kirjaudu → hae tapahtumat")
  void userHappyPath_listEvents() {
    given()
      .auth().preemptive().basic(USERNAME, PASSWORD)
      .accept(ContentType.JSON)
    .when()
      .get(EVENTS)
    .then()
      .statusCode(200)
      .contentType(containsString("json")); // kevyt tarkistus
  }
}
