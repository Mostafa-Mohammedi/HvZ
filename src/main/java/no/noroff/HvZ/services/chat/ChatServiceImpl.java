package no.noroff.HvZ.services.chat;

import no.noroff.HvZ.models.Chat;
import no.noroff.HvZ.models.exceptions.chat.ChatNotFoundException;
import no.noroff.HvZ.repositories.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ChatServiceImpl implements ChatService {

    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    ChatRepository chatRepository;

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
        return chatRepository.save(entity);
    }

    @Override
    public Chat update(Chat entity) {
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
