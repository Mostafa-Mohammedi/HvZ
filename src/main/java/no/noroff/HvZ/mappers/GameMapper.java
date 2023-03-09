package no.noroff.HvZ.mappers;

import no.noroff.HvZ.models.Game;
import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.models.Squad;
import no.noroff.HvZ.models.dto.game.GameDTO;
import no.noroff.HvZ.models.dto.game.GamePostDTO;
import no.noroff.HvZ.models.dto.game.GamePutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface GameMapper {
    @Mapping(target = "squads", source = "squads", qualifiedByName = "squadsToSquadsId")
    @Mapping(target = "players", source = "players", qualifiedByName = "playersToPlayersId")
    GameDTO gameToGameDTO(Game game);
    Game gamePostDTOtoGame(GamePostDTO gamePostDTO);
    Game gamePutDTOtoGame(GamePutDTO gamePutDTO);
    Collection<GameDTO> gameToGameDTO(Collection<Game> game);

    @Named(value = "squadsToSquadsId")
    default Set<Integer> map(Set<Squad> value) {
        if (value == null)
            return null;
        return value.stream()
                .map(s -> s.getId())
                .collect(Collectors.toSet());
    }

    @Named(value = "playersToPlayersId")
    default Set<Integer> map2(Set<Player> value) {
        if (value == null)
            return null;
        return value.stream()
                .map(s -> s.getId())
                .collect(Collectors.toSet());
    }
}
