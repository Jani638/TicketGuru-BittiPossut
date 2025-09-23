package project.hh.ticketguru.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long ticketTypeId;
    private String code;
    private Boolean sold;
    private Boolean used;


    public Ticket(){}

    public Ticket(Long id, Long ticketTypeId, String code, Boolean sold, Boolean used) {
        this.id = id;
        this.ticketTypeId = ticketTypeId;
        this.code = code;
        this.sold = sold;
        this.used = used;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getTicketTypeId() {
        return ticketTypeId;
    }
    public void setTicketTypeId(Long ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Boolean getSold() {
        return sold;
    }
    public void setSold(Boolean sold) {
        this.sold = sold;
    }
    public Boolean getUsed() {
        return used;
    }
    public void setUsed(Boolean used) {
        this.used = used;
    }
}