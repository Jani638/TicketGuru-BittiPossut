package project.hh.ticketguru;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import project.hh.ticketguru.model.Ticket;

public class TicketTest {

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
    public void testMarkAsSold(){

        Ticket ticket = new Ticket();
        ticket.setSold(false);

        // muutetaan myydyksi
        ticket.setSold(true);

        //tarkistetaan että päivittyi
        assertTrue(ticket.getSold());
    }

}
