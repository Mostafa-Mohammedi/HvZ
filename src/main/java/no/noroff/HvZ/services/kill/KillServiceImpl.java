package no.noroff.HvZ.services.kill;

import no.noroff.HvZ.models.Kill;
import no.noroff.HvZ.models.exceptions.Kill.KillNotFoundException;
import no.noroff.HvZ.repositories.KillRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class KillServiceImpl implements KillService{
    private KillRepository killRepository;

    public KillServiceImpl(KillRepository killRepository) {
        this.killRepository = killRepository;
    }

    @Override
    public Kill add(Kill entity) {
        return killRepository.save(entity);
    }

    @Override
    public Kill update(Kill entity) {

        return killRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        killRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Kill findById(Integer id) {
        return killRepository.findById(id).orElseThrow(() -> new KillNotFoundException(id));
    }

    @Override
    public Collection<Kill> findAll() {
        return killRepository.findAll();
    }
}
