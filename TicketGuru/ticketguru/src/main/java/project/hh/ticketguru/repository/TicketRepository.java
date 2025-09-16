package project.hh.ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.hh.ticketguru.model.Ticket;

public interface TicketRepository extends JpaRepository <Ticket, Long> {

}
