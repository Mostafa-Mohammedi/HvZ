package no.noroff.HvZ.models.dto.chat;

import lombok.Data;

@Data
public class ChatPostDTO {
    private String message;
    private boolean is_human_global;
    private boolean is_zombie_global;
    private String chat_time;


}
