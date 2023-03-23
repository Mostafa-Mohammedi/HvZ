package no.noroff.HvZ.models.dto.zombieChat;


import lombok.Data;

import java.util.List;

@Data
public class ZombieChatPutDTO {
    private int id;
    private List<String> chats;
}
