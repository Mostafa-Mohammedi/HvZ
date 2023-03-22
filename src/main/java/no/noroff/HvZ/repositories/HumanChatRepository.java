package no.noroff.HvZ.repositories;

import no.noroff.HvZ.models.HumanChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanChatRepository extends JpaRepository<HumanChat, Integer> {
}
