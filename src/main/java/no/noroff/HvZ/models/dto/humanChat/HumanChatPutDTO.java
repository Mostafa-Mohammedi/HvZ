package no.noroff.HvZ.models.dto.humanChat;


import lombok.Data;

import java.util.List;

@Data
public class HumanChatPutDTO {
    private int id;
    private List<String> chats;
}
