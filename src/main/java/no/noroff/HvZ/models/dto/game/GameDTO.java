package no.noroff.HvZ.models.dto.game;

import lombok.Getter;
import lombok.Setter;
import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.models.Squad;
import java.util.Date;

import java.util.Set;

@Getter
@Setter
public class GameDTO {
    private int id;
    private String title;
    private String gameType;
    private String description;
    private String status;
    private Date date;
    private int maxPlayers;
    private int playerCount;
    private Set<Integer> players;
    private Set<Integer> squads;
}
