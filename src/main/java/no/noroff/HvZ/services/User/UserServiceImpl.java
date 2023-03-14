package no.noroff.HvZ.services.User;
import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.models.User;
import no.noroff.HvZ.models.exceptions.user.UserNotFoundException;
import no.noroff.HvZ.repositories.PlayerRepository;
import no.noroff.HvZ.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;

    public UserServiceImpl(UserRepository userRepository, PlayerRepository playerRepository) {
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
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

    @Override
    public Collection<Player> getPlayers(Integer user_id){
        return userRepository.findById(user_id).get().getPlayers();
    }

    @Override
    public void updatePlayers(int userId, int[] playerIds){
        User user = userRepository.findById(userId).get();
        Set<Player> playerList = new HashSet<>();
        for (int id : playerIds){
            playerList.add(playerRepository.findById(id).get());
        }
        System.out.println("HERR"+ playerList);
        playerList.forEach(p -> {
            p.setUser(user);
            System.out.println("sjekker objektet " + p);
            System.out.println("sjekker spiller " + p.getUser().getPlayers());
            playerRepository.save(p);
            System.out.println("KLARERERERERER");
        });
        System.out.println("sjekk " + user);
    }

}
