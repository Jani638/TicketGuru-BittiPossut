package project.hh.ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.hh.ticketguru.model.TicketType;

@Repository
public interface TicketTypeRepository extends JpaRepository <TicketType, Long>{

}
