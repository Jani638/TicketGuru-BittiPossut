package project.hh.ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import project.hh.ticketguru.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {


}
