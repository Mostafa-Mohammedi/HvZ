package no.noroff.HvZ.repositories;

import no.noroff.HvZ.models.ZombieChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZombieChatRepository extends JpaRepository<ZombieChat, Integer> {
}
