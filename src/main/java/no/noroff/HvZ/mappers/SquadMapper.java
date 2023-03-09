package no.noroff.HvZ.mappers;

import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.models.Squad;
import no.noroff.HvZ.models.dto.SquadDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SquadMapper {
    @Mapping(target = "players", source = "players", qualifiedByName = "playersToPlayersId")
    SquadDTO squadToSquadDTO(Squad squad);
    Collection<SquadDTO> squadToSquadDTO(Collection<Squad> Squad);

    @Named(value = "playersToPlayersId")
    default Set<Integer> map(Set<Player> value) {
        if (value == null)
            return null;
        return value.stream()
                .map(s -> s.getId())
                .collect(Collectors.toSet());
    }

}
