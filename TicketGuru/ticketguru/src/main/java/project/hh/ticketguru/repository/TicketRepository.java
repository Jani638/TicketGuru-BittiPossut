package project.hh.ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import project.hh.ticketguru.model.Ticket;

@Repository

public interface TicketRepository extends JpaRepository <Ticket, Long> {

}
