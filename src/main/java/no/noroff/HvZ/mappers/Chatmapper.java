package no.noroff.HvZ.mappers;

import no.noroff.HvZ.models.Chat;
import no.noroff.HvZ.models.dto.chat.ChatDTO;
import no.noroff.HvZ.models.dto.chat.ChatPostDTO;
import no.noroff.HvZ.models.dto.chat.ChatPutDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface Chatmapper {

    ChatDTO chatToChatDTO(Chat chat);
    Chat ChatPostDTO(ChatPostDTO chatPostDTO);
    Chat ChatPutDTO(ChatPutDTO chatPutDTO);
    Collection<Chat> chatToChatDTOList(Collection<Chat> chat);


}
