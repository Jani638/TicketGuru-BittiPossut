package project.hh.ticketguru;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import project.hh.ticketguru.model.Event;
import project.hh.ticketguru.model.Ticket;
import project.hh.ticketguru.model.TicketType;
import project.hh.ticketguru.repository.EventRepository;
import project.hh.ticketguru.repository.TicketRepository;
import project.hh.ticketguru.repository.TicketTypeRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicketguruApplicationEndToEndTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @Autowired
    private EventRepository eventRepository;

    private Long testTicketTypeId;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/tickets";
    }

    @BeforeEach
    public void setup() {
        // Tyhjentää tietokannan ennen jokaista testiä
        ticketRepository.deleteAll();
        ticketTypeRepository.deleteAll();
        eventRepository.deleteAll();

        // Luo testidataa
        Event testEvent = new Event(null, "Test Event", LocalDateTime.now().plusDays(30), "Test Venue", 1000);
        testEvent = eventRepository.save(testEvent);

        TicketType testTicketType = new TicketType(null, testEvent.getId(), "Test Type", 50.0, 100);
        testTicketType = ticketTypeRepository.save(testTicketType);
        testTicketTypeId = testTicketType.getId();
    }

    @Test
    public void testCreateTicketAndFindByCode() {
        // Luo uuden lipun
        Ticket newTicket = new Ticket(null, testTicketTypeId, "TEST123", false, null);
        
        HttpEntity<Ticket> request = new HttpEntity<>(newTicket);
        ResponseEntity<Ticket> createResponse = restTemplate
            .withBasicAuth("user", "password")
            .postForEntity(getBaseUrl(), request, Ticket.class);
        
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createResponse.getBody()).isNotNull();
        assertThat(createResponse.getBody().getCode()).isEqualTo("TEST123");
        assertThat(createResponse.getBody().getUsed()).isNull();
        
        // Hakee lipun koodin perusteella
        ResponseEntity<Ticket> getResponse = restTemplate
            .withBasicAuth("user", "password")
            .getForEntity(getBaseUrl() + "?code=TEST123", Ticket.class);
        
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody()).isNotNull();
        assertThat(getResponse.getBody().getCode()).isEqualTo("TEST123");
    }

    @Test
    public void testMarkTicketAsUsed() {
        // Luo uuden lipun
        Ticket newTicket = new Ticket(null, testTicketTypeId, "USED123", false, null);
        ResponseEntity<Ticket> createResponse = restTemplate
            .withBasicAuth("user", "password")
            .postForEntity(getBaseUrl(), newTicket, Ticket.class);
        
        Long ticketId = createResponse.getBody().getId();
        LocalDateTime usedTime = LocalDateTime.of(2023, 11, 7, 7, 3, 46);
        
        // Merkitse lippu käytetyksi patchilla
        Ticket patchData = new Ticket();
        patchData.setUsed(usedTime);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Ticket> patchRequest = new HttpEntity<>(patchData, headers);
        
        ResponseEntity<Ticket> patchResponse = restTemplate
            .withBasicAuth("user", "password")
            .exchange(
                getBaseUrl() + "/" + ticketId,
                HttpMethod.PATCH,
                patchRequest,
                Ticket.class
            );
        
        assertThat(patchResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(patchResponse.getBody().getUsed()).isNotNull();
        assertThat(patchResponse.getBody().getUsed()).isEqualTo(usedTime);

        // Varmistaa että lippu on merkitty käytetyksi tietokannassa
        Ticket savedTicket = ticketRepository.findById(ticketId).orElse(null);
        assertThat(savedTicket).isNotNull();
        assertThat(savedTicket.getUsed()).isEqualTo(usedTime);
    }

    @Test
    public void testFindByCodeNotFound() {
        // Yrittää hakea lippua jota ei ole olemassa
        ResponseEntity<String> response = restTemplate
            .withBasicAuth("user", "password")
            .getForEntity(getBaseUrl() + "?code=NOTEXIST", String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetAllTickets() {
        // Luo muutaman lipun
        ticketRepository.save(new Ticket(null, testTicketTypeId, "TICKET1", false, null));
        ticketRepository.save(new Ticket(null, testTicketTypeId, "TICKET2", false, null));

        // Hakee kaikki liput
        ResponseEntity<Ticket[]> response = restTemplate
            .withBasicAuth("user", "password")
            .getForEntity(getBaseUrl(), Ticket[].class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isEqualTo(2);
    }

    @Test
    public void testPatchTicketNotFound() {
        // Yrittää päivittää lippua jota ei ole olemassa
        Ticket patchData = new Ticket();
        patchData.setUsed(LocalDateTime.now());
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Ticket> patchRequest = new HttpEntity<>(patchData, headers);
        
        ResponseEntity<String> response = restTemplate
            .withBasicAuth("user", "password")
            .exchange(
                getBaseUrl() + "/99999",
                HttpMethod.PATCH,
                patchRequest,
                String.class
            );
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testUnauthorizedAccess() {
        // Yrittää hakea lippuja ilman autentikointia
        ResponseEntity<String> response = restTemplate
            .getForEntity(getBaseUrl(), String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void testMarkTicketAsUsedTwice() {
        // Luo uuden lipun ja merkitse sen käytetyksi patchilla
        Ticket newTicket = new Ticket(null, testTicketTypeId, "DOUBLE123", false, null);
        ResponseEntity<Ticket> createResponse = restTemplate
            .withBasicAuth("user", "password")
            .postForEntity(getBaseUrl(), newTicket, Ticket.class);
        
        Long ticketId = createResponse.getBody().getId();
        LocalDateTime firstUse = LocalDateTime.of(2023, 11, 7, 7, 3, 46);
        
        Ticket patchData = new Ticket();
        patchData.setUsed(firstUse);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Ticket> patchRequest = new HttpEntity<>(patchData, headers);
        
        restTemplate
            .withBasicAuth("user", "password")
            .exchange(getBaseUrl() + "/" + ticketId, HttpMethod.PATCH, patchRequest, Ticket.class);

        // Yrittää merkitä uudelleen käytetyksi patchilla
        LocalDateTime secondUse = LocalDateTime.of(2023, 11, 8, 10, 0, 0);
        patchData.setUsed(secondUse);
        patchRequest = new HttpEntity<>(patchData, headers);
        
        ResponseEntity<Ticket> secondPatchResponse = restTemplate
            .withBasicAuth("user", "password")
            .exchange(getBaseUrl() + "/" + ticketId, HttpMethod.PATCH, patchRequest, Ticket.class);
        
        assertThat(secondPatchResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
