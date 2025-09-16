package project.hh.ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.hh.ticketguru.model.Sale;

public interface SaleRepository extends JpaRepository <Sale,Long>{

}
