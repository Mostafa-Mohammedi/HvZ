package no.noroff.HvZ.models.dto.game;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GamePostDTO {
    private String title;
    private String description;
    private String gameType;
    private int maxPlayers;
}
