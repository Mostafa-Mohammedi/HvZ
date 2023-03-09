package no.noroff.HvZ.models.dto;

import lombok.Getter;
import lombok.Setter;
import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.models.Squad;

import java.util.Set;

@Getter
@Setter
public class GameDTO {
    private int id;
    private String title;
    private String description;
    private Set<Integer> players;
    private Set<Integer> squads;
}
