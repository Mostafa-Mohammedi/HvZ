package no.noroff.HvZ.mappers;

import no.noroff.HvZ.models.Kill;
import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.models.dto.kill.KillDTO;
import no.noroff.HvZ.models.dto.kill.KillPostDTO;
import no.noroff.HvZ.models.dto.kill.KillUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface KillMapper {
    @Mapping(target = "player", source = "player", qualifiedByName = "playerToPlayerName")
    KillDTO killMappedToKillDTO(Kill kill);
    Collection<KillDTO> killMappedToKillDTO(Collection<Kill> kill);

    Kill killPostDtoToKill(KillPostDTO killPostDTO);
    Kill killUpdateDtoToKill(KillUpdateDTO killUpdateDTO);

    @Named(value = "playerToPlayerName")
    default String playerToUsername(Player p){
        if (p == null)
            return null;
        return p.getUsername();
    }
}
