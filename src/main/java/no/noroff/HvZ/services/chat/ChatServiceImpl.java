package no.noroff.HvZ.services.chat;

import com.pusher.rest.Pusher;
import com.pusher.rest.data.Result;
import no.noroff.HvZ.models.Chat;
import no.noroff.HvZ.models.Game;
import no.noroff.HvZ.models.exceptions.chat.ChatNotFoundException;
import no.noroff.HvZ.repositories.ChatRepository;
import no.noroff.HvZ.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final GameRepository gameRepository;
    Pusher pusher;

    public ChatServiceImpl(ChatRepository chatRepository, GameRepository gameRepository) {
        this.chatRepository = chatRepository;
        this.gameRepository = gameRepository;
        pusher = new Pusher("1571371", "12be8984736013be627b", "e1cfcea9c1ab72a3eaa0");
        pusher.setCluster("eu");
        pusher.setEncrypted(true);
    }

    @Override
    public Chat findById(Integer id) {
        return chatRepository.findById(id).orElseThrow(() ->
                new ChatNotFoundException(id));
    }

    @Override
    public Collection<Chat> findAll() {
        return chatRepository.findAll();
    }


    @Override
    public Chat add(Chat entity) {
        gameRepository.findById(entity.getGameRef()).get().setChat(entity);
        return chatRepository.save(entity);
    }

    @Override
    public Chat update(Chat entity) {
        List<String> chats = chatRepository.findById(entity.getId()).get().getChats();
        int gameRef = chatRepository.findById(entity.getId()).get().getGameRef();
        pusher.trigger("hvz-noroff", "chat-event", Collections.singletonMap("message", entity.getChats().get(0)));
        chats.add(entity.getChats().get(0));
        entity.setChats(chats);
        entity.setGameRef(gameRef);
        Game game = gameRepository.findById(entity.getGameRef()).get();
        game.setChat(entity);
        gameRepository.save(game);
        entity.setGame(game);
        return chatRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        chatRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }
}
