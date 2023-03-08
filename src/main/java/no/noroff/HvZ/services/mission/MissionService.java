package no.noroff.HvZ.services.mission;

import no.noroff.HvZ.models.Mission;
import no.noroff.HvZ.services.CrudService;

import java.util.Collection;

public interface MissionService extends CrudService<Mission, Integer> {

    @Override
    Mission findById(Integer id);

    @Override
    Collection<Mission> findAll();

    @Override
    Mission add(Mission entity);

    @Override
    Mission update(Mission entity);

    @Override
    void deleteById(Integer id);

    @Override
    boolean exists(Integer id);
}
