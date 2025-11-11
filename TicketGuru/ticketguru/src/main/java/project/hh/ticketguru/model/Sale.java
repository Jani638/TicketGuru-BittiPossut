package project.hh.ticketguru.model;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private Long ticketId;

    private Long eventId;

    private Long customerId;

    
    private Long sellerId;

    
    private LocalDateTime saleDate;

    
    private Double price;

    
    public Sale() {
    }

    public Sale(Long id, Long ticketId, Long customerId, Long sellerId, LocalDateTime saleDate) {
        this.id = id;
        this.ticketId = ticketId;
        this.customerId = customerId;
        this.sellerId = sellerId;
        this.saleDate = saleDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }
    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice (Double price){
        this.price = price;
    }
}