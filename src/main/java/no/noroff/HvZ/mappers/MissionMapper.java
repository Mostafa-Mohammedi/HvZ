package no.noroff.HvZ.mappers;

import no.noroff.HvZ.models.Mission;
import no.noroff.HvZ.models.dto.mission.MissionDTO;
import no.noroff.HvZ.models.dto.mission.MissionPostDTO;
import no.noroff.HvZ.models.dto.mission.MissionPutDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface MissionMapper {
    MissionDTO missionToMissionDTO(Mission mission);

    Mission missionPostDTO(MissionPostDTO missionPostDTO);
    Mission missionPutDTO(MissionPutDTO missionPutDTO);

    Collection<MissionDTO> missionToMissionDTOList(Collection<Mission> missions);
}
