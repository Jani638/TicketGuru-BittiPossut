package project.hh.ticketguru;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import project.hh.ticketguru.model.User;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import project.hh.ticketguru.model.Event;
import project.hh.ticketguru.model.Ticket;
import project.hh.ticketguru.model.TicketType;
import project.hh.ticketguru.repository.EventRepository;
import project.hh.ticketguru.repository.TicketRepository;
import project.hh.ticketguru.repository.TicketTypeRepository;
import project.hh.ticketguru.repository.UserRepository;

@DataJpaTest
public class TicketguruApplicationJUnit {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createNewTicket() {

        // uusi event
        Event event = new Event();
        event.setName("Rock Concert");
        event.setDateTime(LocalDateTime.now().plusDays(20));
        event.setLocation("Arena");
        event.setCapacity(1000);
        eventRepository.save(event);

        // uusi tickettype
        TicketType ticketType = new TicketType();
        ticketType.setName("VIP");
        ticketType.setQuantity(2);
        ticketType.setPrice(20.0);
        ticketType.setEventId(event.getId());
        ticketTypeRepository.save(ticketType);

        // uusi ticket
        Ticket ticket = new Ticket();
        ticket.setTicketTypeId(ticketType.getId());
        ticket.setCode("OK123");
        ticket.setSold(false);
        ticket.setUsed(null);
        Ticket savedTicket = ticketRepository.save(ticket);

        // varmistetaan, että lippu tallentui oikein
        assertNotNull(savedTicket.getId());
        assertEquals("OK123", savedTicket.getCode());
        assertFalse(savedTicket.getSold());
        assertEquals(ticketType.getId(), savedTicket.getTicketTypeId());
    }

    @Test
    public void findByTicketId() {
        // uusi event
        Event event = new Event();
        event.setName("Coldplay");
        event.setDateTime(LocalDateTime.now().plusDays(500));
        event.setLocation("Stadium");
        event.setCapacity(80000);
        eventRepository.save(event);

        // uusi tickettype
        TicketType ticketType = new TicketType();
        ticketType.setName("VIP");
        ticketType.setQuantity(4);
        ticketType.setPrice(500.0);
        ticketType.setEventId(event.getId());
        ticketTypeRepository.save(ticketType);

        // uusi lippu
        Ticket ticket = new Ticket();
        ticket.setTicketTypeId(ticketType.getId());
        ticket.setCode("OKK444");
        ticket.setSold(false);
        ticket.setUsed(null);
        Ticket savedTicket = ticketRepository.save(ticket);

        // haetaan ticket tietokannasta, id:n perusteella ja tarkistetaan arvot
        Ticket foundTicket = ticketRepository.findById(savedTicket.getId()).orElse(null);
        assertNotNull(foundTicket);
        assertEquals(savedTicket.getId(), foundTicket.getId());
        assertEquals("OKK444", foundTicket.getCode());
        assertFalse(foundTicket.getSold());
    }

    @Test
    public void testUser() {
        // uusi käyttäjä
        User user = new User();
        user.setUsername("Moi123");
        user.setPassword("salasana123");
        user.setRole("USER");

        // tallennetaan tietokantaan
        User saved = userRepository.save(user);

        // tarkistetaan että id generointi toimii
        assertNotNull(saved.getId());
    }

    @Test
    public void testCreateTicket() {

        Ticket ticket = new Ticket();
        ticket.setCode("OKOKO");
        ticket.setTicketTypeId(1L);
        ticket.setSold(false);
        ticket.setUsed(null);

        // Tarkistetaan että arvot tallentuivat oikein
        assertEquals("OKOKO", ticket.getCode());
        assertEquals(1L, ticket.getTicketTypeId());
        assertFalse(ticket.getSold());
        assertNull(ticket.getUsed());
    }

    @Test
    public void testMarkAsSold() {

        Ticket ticket = new Ticket();
        ticket.setSold(false);

        // muutetaan myydyksi
        ticket.setSold(true);

        // tarkistetaan että päivittyi
        assertTrue(ticket.getSold());
    }

    @Test
    public void testCreateEvent() {
        Event event = new Event();
        event.setName("Tikkurila Festival");
        event.setDateTime(LocalDateTime.now().plusDays(200));
        event.setLocation("Tikkurila");
        event.setCapacity(7000);

        // tarkistetaan
        assertEquals("Tikkurila Festival", event.getName());
        assertEquals("Tikkurila", event.getLocation());
        assertEquals(7000, event.getCapacity());
        assertNotNull(event.getDateTime());
    }
}
