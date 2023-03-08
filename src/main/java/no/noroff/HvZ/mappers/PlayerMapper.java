package no.noroff.HvZ.mappers;

import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.models.dto.PlayerDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    PlayerDTO playerToPlayerDTO(Player player);
    Collection<PlayerDTO> playerToPlayerDTO(Collection<Player> player);
}
