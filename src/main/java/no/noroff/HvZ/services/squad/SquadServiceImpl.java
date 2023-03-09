package no.noroff.HvZ.services.squad;

import no.noroff.HvZ.models.Squad;
import no.noroff.HvZ.models.exceptions.squad.SquadNotFoundException;
import no.noroff.HvZ.repositories.SquadRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SquadServiceImpl implements SquadService{
    private final SquadRepository squadRepository;

    public SquadServiceImpl(SquadRepository squadRepository) {
        this.squadRepository = squadRepository;
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
    public Collection<Squad> findAll() {
        return squadRepository.findAll();
    }
}
