package project.hh.ticketguru;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import project.hh.ticketguru.model.*;
import project.hh.ticketguru.repository.*;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final EventRepository eventRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final SaleRepository saleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(EventRepository eventRepository,
                      TicketTypeRepository ticketTypeRepository,
                      TicketRepository ticketRepository,
                      UserRepository userRepository,
                      SaleRepository saleRepository,
                      PasswordEncoder passwordEncoder) {
        this.eventRepository = eventRepository;
        this.ticketTypeRepository = ticketTypeRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.saleRepository = saleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Only load demo data if running in development mode
        String profile = System.getenv("SPRING_PROFILES_ACTIVE");
        if (profile != null && profile.contains("rahti")) {
            return; // Skip demo data in production
        }
        
        User user = new User(null, "User", passwordEncoder.encode("user"), "USER");
        User admin = new User(null, "Admin", passwordEncoder.encode("admin"), "ADMIN");
        userRepository.save(user);
        userRepository.save(admin);

        
        Event event1 = new Event(null, "Rock-konsertti", LocalDateTime.of(2025,12,1,19,0), "Helsinki Arena", 5000);
        Event event2 = new Event(null, "Jazz-ilta", LocalDateTime.of(2025,11,20,18,0), "Turku Concert Hall", 3000);
        eventRepository.save(event1);
        eventRepository.save(event2);

        
        TicketType vip = new TicketType(null, event1.getId(), "VIP", 99.9, 50);
        TicketType regular = new TicketType(null, event1.getId(), "Regular", 49.9, 200);
        TicketType jazzRegular = new TicketType(null, event2.getId(), "Regular", 39.9, 150);
        ticketTypeRepository.save(vip);
        ticketTypeRepository.save(regular);
        ticketTypeRepository.save(jazzRegular);

        
        Ticket ticket1 = new Ticket(null, vip.getId(), "VIP001", false, null);
        Ticket ticket2 = new Ticket(null, regular.getId(), "REG001", false, null);
        Ticket ticket3 = new Ticket(null, jazzRegular.getId(), "JAZZ001", false, null);
        ticketRepository.save(ticket1);
        ticketRepository.save(ticket2);
        ticketRepository.save(ticket3);

        
        Sale sale1 = new Sale();
        sale1.setTicketId(ticket1.getId());
        sale1.setSellerId(user.getId());
        sale1.setSaleDate(LocalDateTime.now());
        sale1.setPrice(99.9);
        saleRepository.save(sale1);

        Sale sale2 = new Sale();
        sale2.setTicketId(ticket2.getId());
        sale2.setSellerId(admin.getId());
        sale2.setSaleDate(LocalDateTime.now());
        sale2.setPrice(49.9);
        saleRepository.save(sale2);
    }
}
