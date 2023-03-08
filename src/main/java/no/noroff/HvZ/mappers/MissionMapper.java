package no.noroff.HvZ.mappers;

import no.noroff.HvZ.models.Mission;
import no.noroff.HvZ.models.dto.MissionDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface MissionMapper {
    MissionDTO missionToMissionDTO(Mission mission);

    Collection<MissionDTO> missionToMissionDTOList(Collection<Mission> missions);
}
