package no.noroff.HvZ.services.squad;

import no.noroff.HvZ.models.Game;
import no.noroff.HvZ.models.Squad;
import no.noroff.HvZ.services.CrudService;

import java.util.Collection;

public interface SquadService extends CrudService<Squad, Integer> {
    Squad findById(Integer id);
    Collection<Squad> findAll();
}
