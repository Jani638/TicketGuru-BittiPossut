package project.hh.ticketguru.controller;

import project.hh.ticketguru.model.Event;
import project.hh.ticketguru.repository.EventRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @GetMapping
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Event> getEvent(@PathVariable Long id) {
        return eventRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent(@RequestBody @Valid Event event) {
        return eventRepository.save(event);
    }


    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody @Valid Event updated) {
        Event existing = eventRepository.findById(id)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
        existing.setName(updated.getName());
        existing.setDateTime(updated.getDateTime());
        existing.setLocation(updated.getLocation());
        existing.setCapacity(updated.getCapacity());
        return eventRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventRepository.deleteById(id);
    }
}
