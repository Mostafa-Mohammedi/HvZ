package no.noroff.HvZ.models.dto.chat;

import lombok.Data;

import java.util.List;

@Data
public class ChatDTO {
    private int id;
    private List<String> Chats;
}
