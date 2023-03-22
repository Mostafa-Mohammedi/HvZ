package no.noroff.HvZ.models.dto.zombieChat;

import lombok.Data;

import java.util.List;

@Data
public class ZombieChatDTO {
    private int id;
    private List<String> Chats;
}
