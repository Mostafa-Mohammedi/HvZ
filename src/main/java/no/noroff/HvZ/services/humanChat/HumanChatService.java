package no.noroff.HvZ.services.humanChat;

import no.noroff.HvZ.models.HumanChat;
import no.noroff.HvZ.services.CrudService;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface HumanChatService extends CrudService<HumanChat, Integer> {
    @Override
    HumanChat findById(Integer id);

    @Override
    Collection<HumanChat> findAll();

    @Override
    HumanChat add(HumanChat entity);

    @Override
    HumanChat update(HumanChat entity);

    @Override
    void deleteById(Integer id);

    @Override
    boolean exists(Integer id);
}
