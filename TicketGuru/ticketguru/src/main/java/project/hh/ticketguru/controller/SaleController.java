package project.hh.ticketguru.controller;

import project.hh.ticketguru.model.Sale;
import project.hh.ticketguru.repository.SaleRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleRepository saleRepository;

    public SaleController(SaleRepository saleRepository){
        this.saleRepository = saleRepository;
    }

    @GetMapping
    public List<Sale> getAllSales(){
        return saleRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Sale> getSale(@PathVariable Long id) {
        return saleRepository.findById(id);
    }

    @PostMapping
    public Sale createSale(@RequestBody Sale sale) {
        return saleRepository.save(sale);
    }
    
    @PutMapping("/{id}")
    public Sale updateSale(@PathVariable Long id, @RequestBody Sale updated) {
        Sale existing = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        existing.setTicketId(updated.getTicketId());
        existing.setCustomerId(updated.getCustomerId());
        existing.setSellerId(updated.getSellerId());
        existing.setSaleDate(updated.getSaleDate());
        return saleRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteSale(@PathVariable Long id) {
        saleRepository.deleteById(id);
    }
}