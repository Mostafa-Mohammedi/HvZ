package no.noroff.HvZ.mappers;

import no.noroff.HvZ.models.Kill;
import no.noroff.HvZ.models.dto.KillDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface KillMapper {
    KillDTO killMappedToKillDTO(Kill kill);
    Collection<KillDTO> killMappedToKillDTO(Collection<Kill> kill);
}
