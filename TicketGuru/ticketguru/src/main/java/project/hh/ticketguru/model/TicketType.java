package project.hh.ticketguru.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

<<<<<<< HEAD
=======

>>>>>>> ba3df76276a725581f3b80ea90182795a1d8f1b7
@Entity
public class TicketType {

    @Id
<<<<<<< HEAD
    @GeneratedValue(strategy = GenerationType.AUTO)

=======
    @GeneratedValue(strategy = GenerationType.IDENTITY)
>>>>>>> ba3df76276a725581f3b80ea90182795a1d8f1b7
    private Long id;
    
    private Long eventId;
    private String name;
    private Double price;
    private Integer quantity;

    public TicketType() {
    }

<<<<<<< HEAD
=======
    public TicketType(){}

>>>>>>> ba3df76276a725581f3b80ea90182795a1d8f1b7
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
