package no.noroff.HvZ.services.kill;

import no.noroff.HvZ.models.Game;
import no.noroff.HvZ.models.Kill;
import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.models.exceptions.Kill.KillNotFoundException;
import no.noroff.HvZ.repositories.GameRepository;
import no.noroff.HvZ.repositories.KillRepository;
import no.noroff.HvZ.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class KillServiceImpl implements KillService{
    private KillRepository killRepository;
    private PlayerRepository playerRepository;
    private GameRepository gameRepository;
    public KillServiceImpl(KillRepository killRepository, PlayerRepository playerRepository, GameRepository gameRepository) {
        this.killRepository = killRepository;
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public Kill add(Kill entity) {
        System.out.println(entity.getTime_of_death());
        Player player = playerRepository.findById(entity.getPlayerRef()).get();
        Game game = gameRepository.findById(entity.getGameRef()).get();
        entity.setPlayer(player);
        entity.setGame(game);
        player.setHuman(false);
        return killRepository.save(entity);
    }

    @Override
    public Kill update(Kill entity) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        killRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Kill findById(Integer id) {
        return killRepository.findById(id).orElseThrow(() -> new KillNotFoundException(id));
    }

    @Override
    public Collection<Kill> findAll() {
        return killRepository.findAll();
    }
}
