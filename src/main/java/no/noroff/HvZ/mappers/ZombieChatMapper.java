package no.noroff.HvZ.mappers;

import no.noroff.HvZ.models.ZombieChat;
import no.noroff.HvZ.models.dto.zombieChat.ZombieChatDTO;
import no.noroff.HvZ.models.dto.zombieChat.ZombieChatPostDTO;
import no.noroff.HvZ.models.dto.zombieChat.ZombieChatPutDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface ZombieChatMapper {
    ZombieChatDTO zombieChatToZombieChatDTO(ZombieChat chat);
    ZombieChat ZombieChatPostDTOtoZombieChat(ZombieChatPostDTO chat);
    ZombieChat ZombieChatPutDTOtoZombieChat(ZombieChatPutDTO chat);
    Collection<ZombieChatDTO> chatToChatDTOList(Collection<ZombieChat> chats);


}
