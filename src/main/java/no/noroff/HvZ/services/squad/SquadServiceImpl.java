package no.noroff.HvZ.services.squad;

import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.models.Squad;
import no.noroff.HvZ.models.exceptions.squad.SquadNotFoundException;
import no.noroff.HvZ.repositories.PlayerRepository;
import no.noroff.HvZ.repositories.SquadRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class SquadServiceImpl implements SquadService{
    private final SquadRepository squadRepository;
    private final PlayerRepository playerRepository;

    public SquadServiceImpl(SquadRepository squadRepository, PlayerRepository playerRepository) {
        this.squadRepository = squadRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public Squad add(Squad entity) {
        return squadRepository.save(entity);
    }

    @Override
    public Squad update(Squad entity) {
        return squadRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        squadRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Squad findById(Integer id) {
        return squadRepository.findById(id).orElseThrow(() -> new SquadNotFoundException(id));
    }

    @Override
    public Collection<Player> getPlayers(Integer squad_id){
        return squadRepository.findById(squad_id).get().getPlayers();
    }

    @Override
    public void updatePlayers(int squadId, int[] playerIds) {
        Squad squad= squadRepository.findById(squadId).get();
        Set<Player> playerList = new HashSet<>();

        for (int id : playerIds){
            Player p = playerRepository.findById(id).get();
            p.setSquad(squad);
            playerRepository.save(p);
            playerList.add(p);
        }
    }

    @Override
    public Collection<Squad> findAll() {
        return squadRepository.findAll();
    }
}
