package no.noroff.HvZ.services.squad;

import no.noroff.HvZ.models.Game;
import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.models.Squad;
import no.noroff.HvZ.models.exceptions.squad.SquadNotFoundException;
import no.noroff.HvZ.repositories.GameRepository;
import no.noroff.HvZ.repositories.PlayerRepository;
import no.noroff.HvZ.repositories.SquadRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class SquadServiceImpl implements SquadService{
    private final SquadRepository squadRepository;
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    public SquadServiceImpl(SquadRepository squadRepository, PlayerRepository playerRepository, GameRepository gameRepository) {
        this.squadRepository = squadRepository;
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public Squad add(Squad entity) {
        entity.setGame(gameRepository.findById(entity.getGameRef()).get());
        squadRepository.save(entity);

        int[] playerIds = entity.getPlayerIds();
        Set<Player> players = new HashSet<>();
        for (int playerId : playerIds) {
            players.add(playerRepository.findById(playerId).get());
        }

        players.forEach(s -> {
            s.setSquad(entity);
            playerRepository.save(s);
        });
        return squadRepository.save(entity);
    }

    @Override
    public Squad update(Squad entity) {
        return squadRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        squadRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Squad findById(Integer id) {
        return squadRepository.findById(id).orElseThrow(() -> new SquadNotFoundException(id));
    }

    @Override
    public Collection<Squad> findAll() {
        return squadRepository.findAll();
    }
}
