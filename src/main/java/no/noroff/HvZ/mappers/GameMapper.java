package no.noroff.HvZ.mappers;

import no.noroff.HvZ.models.*;
import no.noroff.HvZ.models.dto.game.GameChatDTO;
import no.noroff.HvZ.models.dto.game.GameDTO;
import no.noroff.HvZ.models.dto.game.GameIdViewDTO;
import no.noroff.HvZ.models.dto.game.GamePostDTO;
import no.noroff.HvZ.models.dto.game.GamePutDTO;
import no.noroff.HvZ.models.dto.squad.SquadDTO;
import no.noroff.HvZ.models.dto.squad.SquadPostDTO;
import no.noroff.HvZ.models.dto.squad.SquadPutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {PlayerMapper.class, KillMapper.class, SquadMapper.class})
public interface GameMapper {
    @Mapping(target = "squads", source = "squads", qualifiedByName = "squadsToSquadsId")
    @Mapping(target = "kills", source = "kills", qualifiedByName = "killsToKillsId")

    @Mapping(target = "players", source = "players", qualifiedByName = "playersToPlayersIds")
    @Mapping(target = "chats", source = "chats", qualifiedByName = "chatsToId")

    GameDTO gameToGameDTO(Game game);
    Game gamePostDTOtoGame(GamePostDTO gamePostDTO);
    Game gamePutDTOtoGame(GamePutDTO gamePutDTO);
    Collection<GameDTO> gameToGameDTO(Collection<Game> game);

    Collection<GameChatDTO> chatListDTO(Collection<Chat> chat);

    GameIdViewDTO gameToGameIdViewDTO(Game game);

    @Named(value = "squadsToSquadsId")
    default Set<Integer> map(Set<Squad> value) {
        if (value == null)
            return null;
        return value.stream()
                .map(s -> s.getId())
                .collect(Collectors.toSet());
    }

    @Named(value = "killsToKillsId")
    default Set<Integer> map1(Set<Kill> value) {
        if (value == null)
            return null;
        return value.stream()
                .map(k -> k.getId())
                .collect(Collectors.toSet());
    }

    @Named(value = "playersToPlayersIds")
    default Set<Integer> map2(Set<Player> value) {
        if (value == null)
            return null;
        return value.stream()
                .map(s -> s.getId())
                .collect(Collectors.toSet());
    }
    @Named(value = "chatsToId")
    default Collection<Integer> chatList(Collection<Chat> value) {
        if (value == null)
            return null;
        return value.stream()
                .map(Chat::getId)
                .collect(Collectors.toSet());
    }

}
