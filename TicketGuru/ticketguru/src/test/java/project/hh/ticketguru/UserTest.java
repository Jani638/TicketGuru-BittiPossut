package project.hh.ticketguru;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import project.hh.ticketguru.model.User;
import project.hh.ticketguru.repository.UserRepository;

@DataJpaTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @Test
    public void testUser() {
        // uusi käyttäjä
        user = new User();
        user.setUsername("Moi123");
        user.setPassword("salasana123");
        user.setRole("USER");

        //tallennetaan tietokantaan
        User saved = userRepository.save(user);

        // tarkistetaan että id generointi toimii
        assertNotNull(saved.getId());
    }

}
