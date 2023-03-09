package no.noroff.HvZ.services.chat;

import no.noroff.HvZ.models.Chat;
import no.noroff.HvZ.services.CrudService;
import org.springframework.stereotype.Service;

import java.util.Collection;

public interface ChatService extends CrudService<Chat, Integer> {


    @Override
    Chat findById(Integer id);

    @Override
    Collection<Chat> findAll();

    @Override
    Chat add(Chat entity);

    @Override
    Chat update(Chat entity);

    @Override
    void deleteById(Integer id);

    @Override
    boolean exists(Integer id);
}
