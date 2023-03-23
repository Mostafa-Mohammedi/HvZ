package no.noroff.HvZ.services.humanChat;

import com.pusher.rest.Pusher;
import no.noroff.HvZ.models.Game;
import no.noroff.HvZ.models.HumanChat;
import no.noroff.HvZ.models.exceptions.chat.ChatNotFoundException;
import no.noroff.HvZ.repositories.GameRepository;
import no.noroff.HvZ.repositories.HumanChatRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class HumanChatServiceImpl implements HumanChatService{

    private final HumanChatRepository humanChatRepository;
    private final GameRepository gameRepository;
    Pusher pusher;

    public HumanChatServiceImpl(HumanChatRepository humanChatRepository, GameRepository gameRepository) {
        this.humanChatRepository = humanChatRepository;
        this.gameRepository = gameRepository;
        pusher = new Pusher("1571371", "12be8984736013be627b", "e1cfcea9c1ab72a3eaa0");
        pusher.setCluster("eu");
        pusher.setEncrypted(true);
    }

    @Override
    public HumanChat findById(Integer id) {
        return humanChatRepository.findById(id).orElseThrow(() ->
                new ChatNotFoundException(id));
    }

    @Override
    public Collection<HumanChat> findAll() {
        return humanChatRepository.findAll();
    }

    @Override
    public HumanChat add(HumanChat entity) {
        gameRepository.findById(entity.getGameRef()).get().setHumanChat(entity);
        return humanChatRepository.save(entity);
    }

    @Override
    public HumanChat update(HumanChat entity) {
        List<String> chats = humanChatRepository.findById(entity.getId()).get().getChats();
        int gameRef = humanChatRepository.findById(entity.getId()).get().getGameRef();
        pusher.trigger("hvz-noroff", "humanChat-event", Collections.singletonMap("message", entity.getChats().get(0)));
        chats.add(entity.getChats().get(0));
        entity.setChats(chats);
        entity.setGameRef(gameRef);
        Game game = gameRepository.findById(entity.getGameRef()).get();
        game.setHumanChat(entity);
        gameRepository.save(game);
        entity.setGame(game);
        return humanChatRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        humanChatRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }
}
