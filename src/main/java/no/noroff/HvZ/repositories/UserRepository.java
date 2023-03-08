package no.noroff.HvZ.repositories;

import no.noroff.HvZ.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    
}
