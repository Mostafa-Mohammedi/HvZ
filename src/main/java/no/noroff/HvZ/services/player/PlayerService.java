package no.noroff.HvZ.services.player;
import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.services.CrudService;
import java.util.Collection;

public interface PlayerService extends CrudService<Player, Integer> {
    Player findById(Integer id);
    Collection<Player> findAll();

    Player playerCheckIn(Player entity, Integer id);
    void deleteByToken(String token);
}
