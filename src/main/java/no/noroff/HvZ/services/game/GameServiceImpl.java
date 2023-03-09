package no.noroff.HvZ.services.game;

import no.noroff.HvZ.models.Game;
import no.noroff.HvZ.models.exceptions.game.GameNotFoundException;
import no.noroff.HvZ.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@Service
public class GameServiceImpl implements GameService{
    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game add(Game entity) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        entity.setStatus("registration");
        entity.setDate(date);
        entity.setPlayerCount(0);
        return gameRepository.save(entity);
    }

    @Override
    public Game update(Game entity) {
        return gameRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        gameRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Game findById(Integer id) {
        return gameRepository.findById(id).orElseThrow(() ->
                new GameNotFoundException(id));
    }

    @Override
    public Collection<Game> findAll() {
        return gameRepository.findAll();
    }
}
