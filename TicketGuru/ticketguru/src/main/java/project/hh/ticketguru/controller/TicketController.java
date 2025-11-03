package project.hh.ticketguru.controller;

import project.hh.ticketguru.model.Ticket;
import project.hh.ticketguru.repository.TicketRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketRepository ticketRepository;

    public TicketController(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    @GetMapping
    public ResponseEntity<?> getTickets(@RequestParam(required = false) String code) {
        if (code != null) {
            return ticketRepository.findByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
        }
        return ResponseEntity.ok(ticketRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public Optional<Ticket> getTicket(@PathVariable Long id) {
        return ticketRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket createTicket(@RequestBody @Valid Ticket ticket) {
        return ticketRepository.save(ticket);
    }
    
    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable Long id, @RequestBody @Valid Ticket updated) {
        Ticket existing = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        existing.setTicketTypeId(updated.getTicketTypeId());
        existing.setCode(updated.getCode());
        existing.setSold(updated.getSold());
        existing.setUsed(updated.getUsed());
        return ticketRepository.save(existing);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ticket> markTicketAsUsed(@PathVariable Long id, @RequestBody Ticket partialUpdate) {
        return ticketRepository.findById(id)
            .map(ticket -> {
                if (partialUpdate.getUsed() != null) {
                    ticket.setUsed(partialUpdate.getUsed());
                }
                return ResponseEntity.ok(ticketRepository.save(ticket));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketRepository.deleteById(id);
    }
}