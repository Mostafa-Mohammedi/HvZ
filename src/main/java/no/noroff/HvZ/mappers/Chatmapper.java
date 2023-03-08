package no.noroff.HvZ.mappers;

import no.noroff.HvZ.models.Chat;
import no.noroff.HvZ.models.dto.ChatDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface Chatmapper {

    ChatDTO chatToChatDTO(Chat chat);
    Collection<Chat> chatToChatDTOList(Collection<Chat> chat);


}
