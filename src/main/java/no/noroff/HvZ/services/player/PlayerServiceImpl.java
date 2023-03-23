package no.noroff.HvZ.services.player;

import no.noroff.HvZ.models.Game;
import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.models.Squad;
import no.noroff.HvZ.models.User;
import no.noroff.HvZ.models.exceptions.player.PlayerNotFoundException;
import no.noroff.HvZ.repositories.GameRepository;
import no.noroff.HvZ.repositories.PlayerRepository;
import no.noroff.HvZ.repositories.SquadRepository;
import no.noroff.HvZ.repositories.UserRepository;
import no.noroff.HvZ.services.User.UserService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final SquadRepository squadRepository;
    private final GameRepository gameRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, UserRepository userRepository, UserService userService, SquadRepository squadRepository, GameRepository gameRepository) {
        this.playerRepository = playerRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.squadRepository = squadRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public Player add(Player entity) {
        User user = userService.findByIdToken(entity.getUserTokenRef());
        Game game = gameRepository.findById(entity.getGameRef()).get();
        entity.setUsername(user.getUsername());
        return playerRepository.save(entity);
    }

    @Override
    public Player update(Player entity) {
        Player player = playerRepository.findById(entity.getId()).get();

        if (entity.getSquadRef() != 0){
            Squad squad = squadRepository.findById(entity.getSquadRef()).get();
            player.setSquad(squad);
        } else {
            player.setSquad(null);
        }
        player.setHuman(entity.isHuman());
        return playerRepository.save(player);
    }

    @Override
    public void deleteById(Integer id) {
        Player player  = playerRepository.findById(id).get();
        player.setGame(null);
        player.setSquad(null);
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
