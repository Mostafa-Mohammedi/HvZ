package no.noroff.HvZ.services.User;

import no.noroff.HvZ.models.User;
import no.noroff.HvZ.services.CrudService;

import java.util.Collection;

public interface UserService extends CrudService<User, Integer> {

    User findById(Integer id);

    Collection<User> findAll();

    User add(User entity);



}
