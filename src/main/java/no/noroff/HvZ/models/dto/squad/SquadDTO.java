package no.noroff.HvZ.models.dto.squad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import no.noroff.HvZ.models.Game;
import no.noroff.HvZ.models.Player;
import no.noroff.HvZ.models.Squad;

import java.util.Set;

@Getter
@Setter
public class SquadDTO {
    private int id;

    private String name;
    private Set<Integer> players;
    private Game game;
}
