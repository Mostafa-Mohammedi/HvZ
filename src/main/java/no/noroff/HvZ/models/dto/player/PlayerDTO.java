package no.noroff.HvZ.models.dto.player;

import lombok.Getter;
import lombok.Setter;
import no.noroff.HvZ.models.Squad;

@Getter
@Setter
public class PlayerDTO {
    private int id;
    private boolean isHuman;
    private String lastCheckInTime;
    private boolean isPatientZero;
    private double lat;
    private double lng;
    private String biteCode;
    private Squad squad;
    private Integer user;
    private Integer game;
    private Set<String> chat;



}
