package no.noroff.HvZ.models.dto.game;

import lombok.Getter;
import lombok.Setter;
import no.noroff.HvZ.models.Kill;
import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.models.Squad;
import no.noroff.HvZ.models.dto.kill.KillDTO;
import no.noroff.HvZ.models.dto.player.PlayerDTO;
import no.noroff.HvZ.models.dto.squad.SquadDTO;

import java.util.Set;

@Getter
@Setter
public class GameIdViewDTO {
    private int id;
    private String title;
    private String gameType;
    private String description;
    private String status;
    private String date;
    private int maxPlayers;
    private int playerCount;
    private Set<PlayerDTO> players;
    private Set<Integer> squads;
    private Set<KillDTO> kills;
}
