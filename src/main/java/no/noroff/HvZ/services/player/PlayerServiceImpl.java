package no.noroff.HvZ.services.player;

import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.models.exceptions.player.PlayerNotFoundException;
import no.noroff.HvZ.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player add(Player entity) {
        return playerRepository.save(entity);
    }

    @Override
    public Player update(Player entity) {
        return playerRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        playerRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return playerRepository.existsById(id);
    }

    @Override
    public Player findById(Integer id) {
        return playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException(id));
    }

    @Override
    public Collection<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player playerCheckIn(Player entity, Integer id) {
        Player player = playerRepository.findById(id).get();
        player.setLng(entity.getLng());
        player.setLat(entity.getLat());
        player.setLastCheckInTime(entity.getLastCheckInTime());
        return playerRepository.save(player);
    }
}
