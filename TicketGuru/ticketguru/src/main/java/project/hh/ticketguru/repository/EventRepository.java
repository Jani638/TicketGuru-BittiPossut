package project.hh.ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event;

public interface EventRepository extends JpaRepository<Event,Long>{

}
