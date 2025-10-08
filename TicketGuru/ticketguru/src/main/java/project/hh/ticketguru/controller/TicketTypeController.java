package project.hh.ticketguru.controller;

import project.hh.ticketguru.model.TicketType;
import project.hh.ticketguru.repository.TicketTypeRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/tickets/types")
public class TicketTypeController {

    private final TicketTypeRepository ticketTypeRepository;

    public TicketTypeController(TicketTypeRepository ticketTypeRepository){
        this.ticketTypeRepository = ticketTypeRepository;
    }

    @GetMapping
    public List<TicketType> getAllTicketTypes(){
        return ticketTypeRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Optional<TicketType> getTicketType(@PathVariable Long id) {
        return ticketTypeRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketType createTicketType(@RequestBody @Valid TicketType ticketType) {
        return ticketTypeRepository.save(ticketType);
    }
    
    @PutMapping("/{id}")
    public TicketType updateTicketType(@PathVariable Long id, @RequestBody @Valid TicketType updated) {
        TicketType existing = ticketTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        existing.setEventId(updated.getEventId());
        existing.setName(updated.getName());
        existing.setPrice(updated.getPrice());
        existing.setQuantity(updated.getQuantity());
        return ticketTypeRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketTypeRepository.deleteById(id);
    }
}