package no.noroff.HvZ.mappers;

import no.noroff.HvZ.models.Game;
import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.models.Squad;
import no.noroff.HvZ.models.dto.squad.SquadDTO;
import no.noroff.HvZ.models.dto.squad.SquadPostDTO;
import no.noroff.HvZ.models.dto.squad.SquadPutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = PlayerMapper.class)
public interface SquadMapper {
    //@Mapping(target = "players", source = "players", qualifiedByName = "playersToPlayersId")
    @Mapping(target = "game", source = "game", qualifiedByName = "gameToGameId")
    SquadDTO squadToSquadDTO(Squad squad);
    Squad squadPostDTO(SquadPostDTO squadPostDTO);
    Squad squadPutDTO(SquadPutDTO squadPutDTO);

    Collection<SquadDTO> squadToSquadDTO(Collection<Squad> squad);

    @Named(value = "playersToPlayersId")
    default Set<Integer> map(Set<Player> value) {
        if (value == null)
            return null;
        return value.stream()
                .map(s -> s.getId())
                .collect(Collectors.toSet());
    }
    @Named(value = "gameToGameId")
    default Integer map1(Game value) {
        if (value == null)
            return null;
        return value.getId();
    }

}
