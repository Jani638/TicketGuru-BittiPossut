package project.hh.ticketguru;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TicketguruApplicationIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testApplicationContextLoads(){
    
    }

    @Test
    public void testGetEventsEndpoint() throws Exception {
        mockMvc.perform(get("/api/events")
                .with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", instanceOf(java.util.List.class)));
    }

    @Test
    public void testCreateEventEndpoint() throws Exception {
        String eventJson = "{\"name\":\"Uusi-konsertti\",\"dateTime\":\"2025-12-01T19:00:00\",\"location\":\"Helsinki Arena\",\"capacity\":5000}";

        mockMvc.perform(post("/api/events")
                .contentType("application/json")
                .content(eventJson)
                .with(httpBasic("user", "password")))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Uusi-konsertti")))
                .andExpect(jsonPath("$.location", equalTo("Helsinki Arena")))
                .andExpect(jsonPath("$.capacity", equalTo(5000)));
    }

    @Test
    public void testUpdateEventEndpoint() throws Exception {
        String updatedEventJson = "{\"name\":\"Päivitetty konsertti\",\"dateTime\":\"2025-12-15T20:00:00\",\"location\":\"Tampere Arena\",\"capacity\":6000}";

        mockMvc.perform(put("/api/events/1")
                .contentType("application/json")
                .content(updatedEventJson)
                .with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Päivitetty konsertti")))
                .andExpect(jsonPath("$.location", equalTo("Tampere Arena")))
                .andExpect(jsonPath("$.capacity", equalTo(6000)));
}
    
    @Test 
    public void testDeleteEvent() throws Exception {
        mockMvc.perform(delete("/api/events/1")
                .with(httpBasic("user", "password")))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetTicketsForEvents() throws Exception {
        mockMvc.perform(get("/api/tickets")
                .with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void testInvalidEventNotFound() throws Exception {
        mockMvc.perform(get("/api/events/99999")
                .with(httpBasic("user", "password")))
                .andExpect(status().isNotFound());
    }
    
}