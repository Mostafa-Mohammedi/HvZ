package no.noroff.HvZ.repositories;

import no.noroff.HvZ.models.Kill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KillRepository extends JpaRepository<Kill,Integer> {
}
