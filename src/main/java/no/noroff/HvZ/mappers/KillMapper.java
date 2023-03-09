package no.noroff.HvZ.mappers;

import no.noroff.HvZ.models.Kill;
import no.noroff.HvZ.models.dto.kill.KillDTO;
import no.noroff.HvZ.models.dto.kill.KillPostDTO;
import no.noroff.HvZ.models.dto.kill.KillUpdateDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface KillMapper {
    KillDTO killMappedToKillDTO(Kill kill);
    Collection<KillDTO> killMappedToKillDTO(Collection<Kill> kill);

    Kill killPostDtoToKill(KillPostDTO killPostDTO);
    Kill killUpdateDtoToKill(KillUpdateDTO killUpdateDTO);
}
