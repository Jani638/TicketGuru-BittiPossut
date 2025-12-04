package project.hh.ticketguru;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseE2eTest {
  protected String BASE_URL;
  protected String USERNAME;
  protected String PASSWORD;

  @BeforeAll
  void setup() {
    BASE_URL = get("BASE_URL");
    USERNAME = get("E2E_USER");
    PASSWORD = get("E2E_PASS");

    if (BASE_URL == null || BASE_URL.isBlank()) {
      BASE_URL = "http://localhost:"; // fallback
    }
    BASE_URL = BASE_URL.replaceAll("/+$", ""); // poista loppuslash

    RestAssured.baseURI = BASE_URL;
    RestAssured.basePath = "/api"; // jos kaikki polut alkavat /api
  }

  protected String get(String key) {
    String v = System.getenv(key);
    return (v != null && !v.isBlank()) ? v : System.getProperty(key);
  }
}