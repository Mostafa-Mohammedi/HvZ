package no.noroff.HvZ.repositories;

import no.noroff.HvZ.models.Squad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SquadRepository extends JpaRepository<Squad, Integer> {
}
