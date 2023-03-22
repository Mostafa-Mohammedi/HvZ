package no.noroff.HvZ.services.zombieChat;

import no.noroff.HvZ.models.ZombieChat;
import no.noroff.HvZ.services.CrudService;

import java.util.Collection;

public interface ZombieChatService extends CrudService<ZombieChat, Integer> {
    @Override
    ZombieChat findById(Integer id);

    @Override
    Collection<ZombieChat> findAll();

    @Override
    ZombieChat add(ZombieChat entity);

    @Override
    ZombieChat update(ZombieChat entity);

    @Override
    void deleteById(Integer id);

    @Override
    boolean exists(Integer id);
}
