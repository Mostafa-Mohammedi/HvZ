package no.noroff.HvZ.mappers;
import no.noroff.HvZ.models.Chat;
import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.models.Squad;
import no.noroff.HvZ.models.dto.player.PlayerCheckInDTO;
import no.noroff.HvZ.models.dto.player.PlayerDTO;
import no.noroff.HvZ.models.dto.player.PlayerPostDTO;
import no.noroff.HvZ.models.dto.player.PlayerUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    @Mapping(target = "user", source = "user.id")
    @Mapping(target = "game", source = "game.id")

    PlayerDTO playerToPlayerDTO(Player player);
    Collection<PlayerDTO> playerToPlayerDTO(Collection<Player> player);

    Player playerPostDtoToPlayer(PlayerPostDTO playerPostDTO);
    Player playerUpdateDtoToPlayer(PlayerUpdateDTO playerUpdateDTO);
    Player playerCheckInDTOtoPlayer(PlayerCheckInDTO playerCheckInDTO);
}
