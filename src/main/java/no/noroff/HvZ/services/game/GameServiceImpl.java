package no.noroff.HvZ.services.game;

import lombok.SneakyThrows;
import no.noroff.HvZ.models.*;
import no.noroff.HvZ.models.exceptions.game.GameNotFoundException;
import no.noroff.HvZ.repositories.*;
import no.noroff.HvZ.services.chat.ChatService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class GameServiceImpl implements GameService{
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    private final KillRepository killRepository;
    private final SquadRepository squadRepository;
    private final ChatRepository chatRepository;
    private final HumanChatRepository humanChatRepository;
    private final ZombieChatRepository zombieChatRepository;
    private final ChatService chatService;

    public GameServiceImpl(GameRepository gameRepository, PlayerRepository playerRepository, KillRepository killRepository, SquadRepository squadRepository, ChatRepository chatRepository, HumanChatRepository humanChatRepository, ZombieChatRepository zombieChatRepository, ChatService chatService) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.killRepository = killRepository;
        this.squadRepository = squadRepository;
        this.chatRepository = chatRepository;
        this.humanChatRepository = humanChatRepository;
        this.zombieChatRepository = zombieChatRepository;
        this.chatService = chatService;
    }

    @Override
    public Game add(Game entity) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        entity.setStatus("Registration");
        entity.setDate(dtf.format(now));
        entity.setPlayerCount(0);
        gameRepository.save(entity);
        Chat chat = new Chat();
        chat.setGameRef(entity.getId());
        chat.setChats(new ArrayList<>());
        chat.setGame(entity);
        chatService.add(chat);
        chatRepository.save(chat);
        System.out.println(chatRepository.findById(entity.getId()-2).get());
        entity.setChat(chatRepository.findById(entity.getId()-2).get());
        System.out.println("CREATING GAME");
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

    public Collection<Kill> getKills(Integer id) {
        return gameRepository.findById(id).get().getKills();
    }

    @Override
    public void updateKills(int gameId, int[] killIds) {
        Game game = gameRepository.findById(gameId).get();
        Set<Kill> killList = new HashSet<>();

        for (int id : killIds){
            killList.add(killRepository.findById(id).get());
        }

        killList.forEach(k -> {
            k.setGame(game);
            killRepository.save(k);
        });
    }

    @Override
    public Collection<Squad> getSquads(Integer game_id){
        return gameRepository.findById(game_id).get().getSquads();
    }

    @Override
    public void updateSquads(int gameId, int[] squadsIds){
        Game game = gameRepository.findById(gameId).get();
        Set<Squad> squadList = new HashSet<>();

        for (int id : squadsIds){
            squadList.add(squadRepository.findById(id).get());
        }

        squadList.forEach(s -> {
            s.setGame(game);
            squadRepository.save(s);
        });
    }
}
