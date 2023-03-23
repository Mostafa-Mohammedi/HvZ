package no.noroff.HvZ.models.dto.player;

import lombok.Getter;
import lombok.Setter;
import no.noroff.HvZ.models.Squad;

import java.util.Set;

@Getter
@Setter
public class PlayerDTO {
    private int id;
    private boolean isHuman;

    private String username;
    private String lastCheckInTime;
    private boolean isPatientZero;
    private double lat;
    private double lng;
    private String biteCode;
    private Squad squad;
    private Integer game;
}
