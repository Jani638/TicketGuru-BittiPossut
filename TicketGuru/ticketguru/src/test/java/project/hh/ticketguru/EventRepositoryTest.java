package project.hh.ticketguru;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import project.hh.ticketguru.model.Event;
import project.hh.ticketguru.repository.EventRepository;

@DataJpaTest
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    // hae tapahtuma id:llä
    @Test
    public void findByEventId() {

        //uusi tapahtuma
        Event event = new Event();
        event.setName("Rock Concert");
        event.setDateTime(LocalDateTime.now().plusDays(20));
        event.setLocation("Arena");
        event.setCapacity(1000);
        eventRepository.save(event);

        // haetaan tapahtuma tietokannasta id:llä
        Event found = eventRepository.findById(event.getId()).orElse(null);

        // varmistetaan että löytyy
        assertNotNull(found);

        //tarkistetaan haetut tiedot
        assertEquals(event.getName(), found.getName());
        assertEquals(event.getCapacity(), found.getCapacity());
        assertEquals(event.getCapacity(), found.getCapacity());
    }
}
