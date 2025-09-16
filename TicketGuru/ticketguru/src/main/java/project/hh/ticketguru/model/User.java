package project.hh.ticketguru.model;

public class User {

    private Long id;
    private String username;
    private String password;
    private Enum role;


    public User(){}
    
    public User(Long id, String username, String password, Enum role) {
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
    public Enum getRole() {
        return role;
    }
    public void setRole(Enum role) {
        this.role = role;
    }
    

}
