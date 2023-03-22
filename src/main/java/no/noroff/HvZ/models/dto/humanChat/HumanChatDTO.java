package no.noroff.HvZ.models.dto.humanChat;

import lombok.Data;

import java.util.List;

@Data
public class HumanChatDTO {
    private int id;
    private List<String> Chats;
}
