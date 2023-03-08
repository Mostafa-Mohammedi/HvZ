package no.noroff.HvZ.mappers;

import no.noroff.HvZ.models.Game;
import no.noroff.HvZ.models.dto.GameDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface GameMapper {
    GameDTO gameToGameDTO(Game game);
    Collection<GameDTO> gameToGameDTO(Collection<Game> game);
}
