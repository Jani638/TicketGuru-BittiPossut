package project.hh.ticketguru;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import project.hh.ticketguru.model.Event;
import project.hh.ticketguru.model.Ticket;
import project.hh.ticketguru.model.TicketType;
import project.hh.ticketguru.repository.EventRepository;
import project.hh.ticketguru.repository.TicketRepository;
import project.hh.ticketguru.repository.TicketTypeRepository;

@DataJpaTest
public class TicketRepositoryTest {

    @Autowired
    private TicketRepository repository;

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void createNewTicket() {

        // uusi tapahtuma
        Event event = new Event();
        event.setName("Rock Concert");
        event.setDateTime(LocalDateTime.now().plusDays(20));
        event.setLocation("Arena");
        event.setCapacity(1000);
        eventRepository.save(event);

        // uusi lippu tyyppi
        TicketType ticketType = new TicketType();
        ticketType.setName("VIP");
        ticketType.setQuantity(2);
        ticketType.setPrice(20.0);
        ticketType.setEventId(event.getId());
        ticketTypeRepository.save(ticketType);

        // uusi lippu
        Ticket ticket = new Ticket();
        ticket.setTicketTypeId(ticketType.getId());
        ticket.setCode("OK123");
        ticket.setSold(false);
        ticket.setUsed(null);
        repository.save(ticket);

        Ticket savedTicket = repository.save(ticket);

        assertNotNull(savedTicket.getId());
        assertEquals("OK123", savedTicket.getCode());
        assertFalse(savedTicket.getSold());
        assertEquals(ticketType.getId(), savedTicket.getTicketTypeId());
    }

    @Test
    public void deleteTicket() {

        // uusi tapahtuma
        Event event = new Event();
        event.setName("RuisRock");
        event.setDateTime(LocalDateTime.now().plusDays(200));
        event.setLocation("Turku");
        event.setCapacity(20000);
        eventRepository.save(event);

        // uusi lippu tyyppi
        TicketType ticketType = new TicketType();
        ticketType.setName("Basic");
        ticketType.setQuantity(1);
        ticketType.setPrice(150.0);
        ticketType.setEventId(event.getId());
        ticketTypeRepository.save(ticketType);

        // uusi lippu
        Ticket ticket = new Ticket();
        ticket.setTicketTypeId(ticketType.getId());
        ticket.setCode("OK321");
        ticket.setSold(false);
        ticket.setUsed(null);
        repository.save(ticket);

        Ticket savedTicket = repository.save(ticket);

        // poista lippu
        repository.delete(savedTicket);

        // tarkistetaan, että lippua ei enää löydy
        assertThat(repository.findById(savedTicket.getId())).isEmpty();
    }

    @Test
    public void findByTicketId() {

        // uusi tapahtuma
        Event event = new Event();
        event.setName("Coldplay");
        event.setDateTime(LocalDateTime.now().plusDays(500));
        event.setLocation("Stadium");
        event.setCapacity(80000);
        eventRepository.save(event);

        // uusi lippu tyyppi
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
        repository.save(ticket);

        Ticket savedTicket = repository.save(ticket);

        Ticket foundTicket = repository.findById(savedTicket.getId()).orElse(null);

        assertNotNull(foundTicket);
        assertEquals(savedTicket.getId(), foundTicket.getId());
        assertEquals("OKK444", foundTicket.getCode());
        assertFalse(foundTicket.getSold());
    }
}
