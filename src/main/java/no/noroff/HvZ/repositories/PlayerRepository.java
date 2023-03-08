package no.noroff.HvZ.repositories;

import no.noroff.HvZ.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
