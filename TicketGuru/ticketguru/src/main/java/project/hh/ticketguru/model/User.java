package project.hh.ticketguru.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity(name="app_user") //Entityn nimi nyt "app_user", koska User nimi on joku varattu avainsana sqlssä nii se tietokanta menee tyhmäksi muuten jos laittais vaan "User"//

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    
    private String username;
    private String password;

    private String role;


    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }


    public User(){}

    public User(Long id, String username, String password, String role) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {

        this.role = role;
    }

    public enum Role {
    USER,
    ADMIN
}
}
