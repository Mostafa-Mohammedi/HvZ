package no.noroff.HvZ.models.dto.game;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GamePutDTO {
    private int id;
    private String title;
    private String status;
    private String description;
    private String gameType;
    private int maxPlayers;
}
