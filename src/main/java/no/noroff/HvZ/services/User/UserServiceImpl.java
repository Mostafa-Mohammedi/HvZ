package no.noroff.HvZ.services.User;
import no.noroff.HvZ.models.User;
import no.noroff.HvZ.models.exceptions.user.UserNotFoundException;
import no.noroff.HvZ.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User update(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);


    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User add(User entity) {
        return userRepository.save(entity);
    }
}
