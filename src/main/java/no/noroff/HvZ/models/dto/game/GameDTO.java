package no.noroff.HvZ.models.dto.game;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class GameDTO {
    private int id;
    private String title;
    private String gameType;
    private String description;
    private String status;
    private String date;
    private int maxPlayers;
    private int playerCount;
    private Set<Integer> players;
    private Set<Integer> squads;
    private Set<Integer> kills;
    private Collection<String> chats;
}
