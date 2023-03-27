package no.noroff.HvZ.models.dto.chat;


import lombok.Data;

import java.util.List;

@Data
/**
 * DTO for updating the chats
 */
public class ChatPutDTO {
    private int id;
    private List<String> chats;
}
