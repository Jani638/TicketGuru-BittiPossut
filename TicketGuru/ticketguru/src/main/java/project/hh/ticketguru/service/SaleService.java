package project.hh.ticketguru.service;

import org.springframework.stereotype.Service;
import project.hh.ticketguru.dto.SaleCreateDto;
import project.hh.ticketguru.model.Sale;
import project.hh.ticketguru.repository.SaleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Optional<Sale> getSale(Long id) {
        return saleRepository.findById(id);
    }

    public Sale createSale(SaleCreateDto dto) {
        Sale sale = new Sale();
        sale.setTicketId(dto.getTicketId());
        sale.setCustomerId(dto.getCustomerId());
        sale.setSellerId(dto.getSellerId());
        sale.setSaleDate(LocalDateTime.now()); // asetetaan backendissä
        return saleRepository.save(sale);
    }

    public Sale updateSale(Long id, Sale updated) {
        Sale existing = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found"));
        existing.setTicketId(updated.getTicketId());
        existing.setCustomerId(updated.getCustomerId());
        existing.setSellerId(updated.getSellerId());
        existing.setSaleDate(updated.getSaleDate());
        return saleRepository.save(existing);
    }

    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }
}
