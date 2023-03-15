package no.noroff.HvZ.services.squad;

import no.noroff.HvZ.models.Game;
import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.models.Squad;
import no.noroff.HvZ.services.CrudService;

import java.util.Collection;

public interface SquadService extends CrudService<Squad, Integer> {
    Squad findById(Integer id);
    Collection<Squad> findAll();

    Collection<Player> getPlayers(Integer game_id);

    void updatePlayers(int gameId, int[] playerIds);

}
