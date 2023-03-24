package no.noroff.HvZ.services.mission;


import no.noroff.HvZ.models.Mission;
import no.noroff.HvZ.models.exceptions.Mission.MissionNotFoundException;
import no.noroff.HvZ.repositories.MissionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MissionServiceImpl implements MissionService{

    private MissionRepository missionRepository;

    public MissionServiceImpl(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }


    @Override
    public Mission findById(Integer id) {
        return missionRepository.findById(id).orElseThrow(() -> new MissionNotFoundException(id));
    }


    @Override
    public Collection<Mission> findAll() {
        return missionRepository.findAll();
    }

    @Override
    public Mission add(Mission entity) {
        return missionRepository.save(entity);
    }

    @Override
    public Mission update(Mission entity) {
        return missionRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        var mission = missionRepository.findById(id).orElseThrow(() -> new MissionNotFoundException(id));
        if(mission.getGame() != null){
            mission.setGame(null);
        }
        missionRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }
}
