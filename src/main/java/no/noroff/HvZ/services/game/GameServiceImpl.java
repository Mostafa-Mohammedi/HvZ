package no.noroff.HvZ.services.game;

import no.noroff.HvZ.models.*;
import no.noroff.HvZ.models.exceptions.game.GameNotFoundException;
import no.noroff.HvZ.repositories.*;
import no.noroff.HvZ.services.chat.ChatService;
import no.noroff.HvZ.services.humanChat.HumanChatService;
import no.noroff.HvZ.services.zombieChat.ZombieChatService;
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
    private final HumanChatService humanChatService;
    private final ZombieChatService zombieChatService;

    public GameServiceImpl(GameRepository gameRepository, PlayerRepository playerRepository, KillRepository killRepository, SquadRepository squadRepository, ChatRepository chatRepository, HumanChatRepository humanChatRepository, ZombieChatRepository zombieChatRepository, ChatService chatService, HumanChatService humanChatService, ZombieChatService zombieChatService) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.killRepository = killRepository;
        this.squadRepository = squadRepository;
        this.chatRepository = chatRepository;
        this.humanChatRepository = humanChatRepository;
        this.zombieChatRepository = zombieChatRepository;
        this.chatService = chatService;
        this.humanChatService = humanChatService;
        this.zombieChatService = zombieChatService;
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
        entity.setChat(chatRepository.findById(entity.getId()).get());

        HumanChat humanChat = new HumanChat();
        humanChat.setGameRef(entity.getId());
        humanChat.setChats(new ArrayList<>());
        humanChat.setGame(entity);
        humanChatService.add(humanChat);
        humanChatRepository.save(humanChat);
        entity.setHumanChat(humanChatRepository.findById(entity.getId()).get());

        ZombieChat zombieChat = new ZombieChat();
        zombieChat.setGameRef(entity.getId());
        zombieChat.setChats(new ArrayList<>());
        zombieChat.setGame(entity);
        zombieChatService.add(zombieChat);
        zombieChatRepository.save(zombieChat);
        entity.setZombieChat(zombieChatRepository.findById(entity.getId()).get());
        return gameRepository.save(entity);
    }

    @Override
    public Game update(Game entity) {
        entity.setDate(findById(entity.getId()).getDate());
        return gameRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        try {
            var game = gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
            var listOfPlayer = game.getPlayers();
            var listOfSquads = game.getSquads();
            var listofKills = game.getKills();
            var chat = game.getChat();


            if (listOfPlayer != null) {
                Iterator<Player> players = listOfPlayer.iterator();
                while (players.hasNext()) {
                    Player player = players.next();
                    player.setGame(null);
                    players.remove();
                }
            }

            if (listOfSquads != null) {
                Iterator<Squad> squads = listOfSquads.iterator();
                while (squads.hasNext()) {
                    var squad = squads.next();
                    squad.setGame(null);
                    squads.remove();
                }
            }

            if (listofKills != null) {
                Iterator<Kill> kills = listofKills.iterator();
                while (kills.hasNext()) {
                    var kill = kills.next();
                    kill.setGame(null);
                    kills.remove();
                }
            }

            chat.setGame(null);
            game.setChat(null);

            game.getHumanChat().setGame(null);
            game.setHumanChat(null);

            game.getZombieChat().setGame(null);
            game.setZombieChat(null);

            gameRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete game with id: " + id, e);
        }
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
