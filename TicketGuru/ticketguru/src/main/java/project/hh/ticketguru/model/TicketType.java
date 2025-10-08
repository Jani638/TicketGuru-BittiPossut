package project.hh.ticketguru.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class TicketType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "EventId cannot be null")
    private Long eventId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Price is required")
    @Min(1)
    private Double price;

    @NotNull(message = "Quantity must be over 0")
    @Min(1)
    private Integer quantity;

    public TicketType() {
    }

    public TicketType(Long id, Long eventId, String name, Double price, Integer quantity) {
        this.id = id;
        this.eventId = eventId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}