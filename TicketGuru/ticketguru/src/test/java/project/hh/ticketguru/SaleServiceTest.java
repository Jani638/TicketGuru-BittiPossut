package project.hh.ticketguru;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import project.hh.ticketguru.dto.SaleCreateDto;
import project.hh.ticketguru.model.Sale;
import project.hh.ticketguru.repository.SaleRepository;
import project.hh.ticketguru.service.SaleService;

@SpringBootTest
public class SaleServiceTest {

    @Autowired
    private SaleService saleService;

    @Autowired
    private SaleRepository saleRepository;

    @Test
    void createAndFindTest() {
        //uusi myynti
        SaleCreateDto dto = new SaleCreateDto();
        dto.setPrice(20.0);

        Sale savedSale = saleService.createSale(dto);

        //tarkistetaan että id luonti onnistui tietokantaan
        assertThat(savedSale.getId()).isNotNull();

        // haetaan tietokannasta
        Sale found = saleRepository.findById(savedSale.getId()).orElseThrow();
        assertThat(found.getPrice()).isEqualTo(20.0);
    }
}
