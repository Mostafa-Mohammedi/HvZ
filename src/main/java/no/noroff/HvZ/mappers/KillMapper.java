package no.noroff.HvZ.mappers;

import jdk.jfr.Name;
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
    @Mapping(target = "playerName", source = "player", qualifiedByName = "playerToPlayerName")
    @Mapping(target = "squadName", source = "player", qualifiedByName = "playerToSquadName")
    @Mapping(target = "biteCode", source = "player", qualifiedByName = "playerToBiteCode")
    KillDTO killMappedToKillDTO(Kill kill);
    Collection<KillDTO> killMappedToKillDTO(Collection<Kill> kill);

    Kill killPostDtoToKill(KillPostDTO killPostDTO);
    Kill killUpdateDtoToKill(KillUpdateDTO killUpdateDTO);

    @Named(value = "playerToPlayerName")
    default String playerToPlayerName(Player p){
        if (p == null || p.getUser() == null)
            return null;
        return p.getUser().getUsername();
    }
    @Named(value = "playerToSquadName")
    default String playerToSquadName(Player p){
        if (p == null || p.getSquad() == null)
            return null;
        return p.getSquad().getName();
    }
    @Named(value = "playerToBiteCode")
    default String playerToBiteCode(Player p){
        if (p == null)
            return null;
        return p.getBiteCode();
    }
}
