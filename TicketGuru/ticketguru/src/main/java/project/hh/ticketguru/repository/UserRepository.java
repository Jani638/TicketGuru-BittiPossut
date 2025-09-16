package project.hh.ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.hh.ticketguru.model.User;

public interface UserRepository extends JpaRepository <User, Long>{

}
