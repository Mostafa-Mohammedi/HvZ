package no.noroff.HvZ.services.game;

import lombok.SneakyThrows;
import no.noroff.HvZ.models.Game;
import no.noroff.HvZ.models.Kill;
import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.models.exceptions.game.GameNotFoundException;
import no.noroff.HvZ.repositories.GameRepository;
import no.noroff.HvZ.repositories.KillRepository;
import no.noroff.HvZ.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class GameServiceImpl implements GameService{
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final KillRepository killRepository;

    public GameServiceImpl(GameRepository gameRepository, PlayerRepository playerRepository, KillRepository killRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.killRepository = killRepository;
    }

    @SneakyThrows
    @Override
    public Game add(Game entity) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        entity.setStatus("registration");
        entity.setDate(dtf.format(now));
        entity.setPlayerCount(0);
        return gameRepository.save(entity);
    }

    @Override
    public Game update(Game entity) {
        entity.setDate(findById(entity.getId()).getDate());
        return gameRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        gameRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Game findById(Integer id) {
        return gameRepository.findById(id).orElseThrow(() ->
                new GameNotFoundException(id));
    }

    @Override
    public Collection<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Collection<Player> getPlayers(Integer game_id){
        return gameRepository.findById(game_id).get().getPlayers();
    }

    @Override
    public void updatePlayers(int gameId, int[] playerIds){
        Game game = gameRepository.findById(gameId).get();
        Collection<Player> oldPlayers = game.getPlayers();
        oldPlayers.forEach(p -> {
            p.setGame(null);
        });
        
        Set<Player> playerList = new HashSet<>();

        for (int id : playerIds){
            playerList.add(playerRepository.findById(id).get());
        }

        playerList.forEach(p -> {
            p.setGame(game);
            playerRepository.save(p);
        });
    }

    @Override
    public Collection<Kill> getKills(Integer game_id){
        return gameRepository.findById(game_id).get().getKills();
    }

    @Override
    public void updateKills(int gameId, int[] killIds){
        Game game = gameRepository.findById(gameId).get();
        Collection<Kill> oldKills = game.getKills();
        oldKills.forEach(k -> {
            k.setGame(null);
        });

        Set<Kill> killList = new HashSet<>();

        for (int id : killIds){
            killList.add(killRepository.findById(id).get());
        }

        killList.forEach(k -> {
            k.setGame(game);
            killRepository.save(k);
        });
    }
}
