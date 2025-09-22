package project.hh.ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.hh.ticketguru.model.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

}
