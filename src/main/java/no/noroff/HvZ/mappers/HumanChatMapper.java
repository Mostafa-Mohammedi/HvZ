package no.noroff.HvZ.mappers;

import no.noroff.HvZ.models.Chat;
import no.noroff.HvZ.models.HumanChat;
import no.noroff.HvZ.models.dto.humanChat.HumanChatDTO;
import no.noroff.HvZ.models.dto.humanChat.HumanChatPostDTO;
import no.noroff.HvZ.models.dto.humanChat.HumanChatPutDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface HumanChatMapper {
    HumanChatDTO humanChatToHumanChatDTO(HumanChat chat);
    HumanChat HumanChatPostDTOtoHumanChat(HumanChatPostDTO chat);
    HumanChat HumanChatPutDTOtoHumanChat(HumanChatPutDTO chat);
    Collection<HumanChatDTO> chatToChatDTOList(Collection<HumanChat> chats);


}
