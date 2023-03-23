package no.noroff.HvZ.services.zombieChat;

import com.pusher.rest.Pusher;
import no.noroff.HvZ.models.Game;
import no.noroff.HvZ.models.ZombieChat;
import no.noroff.HvZ.models.exceptions.chat.ChatNotFoundException;
import no.noroff.HvZ.repositories.GameRepository;
import no.noroff.HvZ.repositories.ZombieChatRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class ZombieChatServiceImpl implements ZombieChatService {

    private final ZombieChatRepository zombieChatRepository;
    private final GameRepository gameRepository;
    Pusher pusher;

    public ZombieChatServiceImpl(ZombieChatRepository zombieChatRepository, GameRepository gameRepository) {
        this.zombieChatRepository = zombieChatRepository;
        this.gameRepository = gameRepository;
        pusher = new Pusher("1571371", "12be8984736013be627b", "e1cfcea9c1ab72a3eaa0");
        pusher.setCluster("eu");
        pusher.setEncrypted(true);
    }

    @Override
    public ZombieChat findById(Integer id) {
        return zombieChatRepository.findById(id).orElseThrow(() ->
                new ChatNotFoundException(id));
    }

    @Override
    public Collection<ZombieChat> findAll() {
        return zombieChatRepository.findAll();
    }

    @Override
    public ZombieChat add(ZombieChat entity) {
        gameRepository.findById(entity.getGameRef()).get().setZombieChat(entity);
        return zombieChatRepository.save(entity);
    }

    @Override
    public ZombieChat update(ZombieChat entity) {
        List<String> chats = zombieChatRepository.findById(entity.getId()).get().getChats();
        int gameRef = zombieChatRepository.findById(entity.getId()).get().getGameRef();
        pusher.trigger("hvz-noroff", "zombieChat-event", Collections.singletonMap("message", entity.getChats().get(0)));
        chats.add(entity.getChats().get(0));
        entity.setChats(chats);
        entity.setGameRef(gameRef);
        Game game = gameRepository.findById(entity.getGameRef()).get();
        game.setZombieChat(entity);
        gameRepository.save(game);
        entity.setGame(game);
        return zombieChatRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        zombieChatRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }
}
