package no.noroff.HvZ.services.game;

import lombok.SneakyThrows;
import no.noroff.HvZ.models.Chat;
import no.noroff.HvZ.models.Game;
import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.models.dto.chat.ChatPostDTO;
import no.noroff.HvZ.models.exceptions.game.GameNotFoundException;
import no.noroff.HvZ.repositories.ChatRepository;
import no.noroff.HvZ.repositories.GameRepository;
import no.noroff.HvZ.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class GameServiceImpl implements GameService{
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private  final ChatRepository chatRepository;

    public GameServiceImpl(GameRepository gameRepository, PlayerRepository playerRepository, ChatRepository chatRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.chatRepository = chatRepository;
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
    public Collection<Chat> getChats(Integer game_id){
        Collection<Chat> gameChat =  gameRepository.findById(game_id).get().getChats();
        return gameChat;
    }
}
