package no.noroff.HvZ.services.game;

import no.noroff.HvZ.models.Chat;
import no.noroff.HvZ.models.Game;
import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.services.CrudService;

import java.util.Collection;

public interface GameService extends CrudService<Game, Integer> {
    Game findById(Integer id);
    Collection<Game> findAll();
    Collection<Player> getPlayers(Integer id);

    void updatePlayers(int gameId, int[] playerIds);
    Collection<Chat> getChats(Integer game_id);
}
