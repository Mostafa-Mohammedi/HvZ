package no.noroff.HvZ.mappers;

import no.noroff.HvZ.models.SquadMember;
import no.noroff.HvZ.models.dto.squadMember.SquadMemberDTO;
import no.noroff.HvZ.models.dto.squadMember.SquadMemberPostDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface SquadMemberMapper {

    @Mapping(target = "player", source="player")
    SquadMemberDTO squadMemberToSquadMemberDTO(SquadMember squadMember);

    SquadMember squadMemberDTOPostSquadMember(SquadMemberPostDTO squadMemberPostDTO);
    Collection<SquadMemberDTO> squadMemberToSquadMemberDTOList(Collection<SquadMember> squadMember);



}
