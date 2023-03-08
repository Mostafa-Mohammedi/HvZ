package no.noroff.HvZ.services.player;

import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.models.exceptions.player.PlayerNotFoundException;
import no.noroff.HvZ.repositories.PlayerRepository;

import java.util.Collection;

public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player add(Player entity) {
        return null;
    }

    @Override
    public Player update(Player entity) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Player findById(Integer id) {
        return playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException(id));
    }

    @Override
    public Collection<Player> findAll() {
        return playerRepository.findAll();
    }
}
