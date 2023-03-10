package no.noroff.HvZ.services.squadMember;

import no.noroff.HvZ.models.SquadMember;
import no.noroff.HvZ.models.exceptions.squadMember.SquadMemberNotFound;
import no.noroff.HvZ.repositories.SquagMemberRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SquadMemberServiceImp implements SquadMemberService {

    private SquagMemberRepository squadMemberRepository;

    public SquadMemberServiceImp(SquagMemberRepository squadMemberRepository) {
        this.squadMemberRepository = squadMemberRepository;
    }


    @Override
    public SquadMember findById(Integer id) {
        return squadMemberRepository.findById(id).orElseThrow(() -> new SquadMemberNotFound(id));
    }


    @Override
    public Collection<SquadMember> findAll() {
        return squadMemberRepository.findAll();
    }

    @Override
    public SquadMember add(SquadMember entity) {
        return squadMemberRepository.save(entity);
    }

    @Override
    public SquadMember update(SquadMember entity) {
        return squadMemberRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        squadMemberRepository.deleteById(id);

    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }
}
