package no.noroff.HvZ.services.game;

import no.noroff.HvZ.models.Game;
import no.noroff.HvZ.services.CrudService;

import java.util.Collection;

public interface GameService extends CrudService<Game, Integer> {
    Game findById(Integer id);
    Collection<Game> findAll();
}
