package no.noroff.HvZ.mappers;

import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.models.dto.player.PlayerDTO;
import no.noroff.HvZ.models.dto.player.PlayerPostDTO;
import no.noroff.HvZ.models.dto.player.PlayerUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    @Mapping(target = "user", source = "user.id")
    @Mapping(target = "game", source = "game.id")

    PlayerDTO playerToPlayerDTO(Player player);
    Collection<PlayerDTO> playerToPlayerDTO(Collection<Player> player);


    Player playerPostDtoToPlayer(PlayerPostDTO playerDTO);
    Player playerUpdateDtoToPlayer(PlayerUpdateDTO playerDTO);
}
