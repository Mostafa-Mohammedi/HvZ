package no.noroff.HvZ.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDTO {
    private int playerId;
    private boolean isHuman;
    private boolean isPatientZero;
    private String biteCode;
}
