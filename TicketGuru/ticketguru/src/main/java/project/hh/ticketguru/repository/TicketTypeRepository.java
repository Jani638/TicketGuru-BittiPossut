package project.hh.ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.hh.ticketguru.model.TicketType;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {

}
