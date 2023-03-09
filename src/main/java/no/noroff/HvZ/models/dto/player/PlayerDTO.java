package no.noroff.HvZ.models.dto.player;

import lombok.Getter;
import lombok.Setter;
import no.noroff.HvZ.models.Game;
import no.noroff.HvZ.models.Squad;
import no.noroff.HvZ.models.User;

import java.util.Set;

@Getter
@Setter
public class PlayerDTO {
    private int id;
    private boolean isHuman;
    private boolean isPatientZero;
    private String biteCode;
    private Squad squad;
    private User user;
    private Game game;

}
