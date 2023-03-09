package no.noroff.HvZ.models.dto.player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerPostDTO {
    private boolean isHuman;
    private boolean isPatientZero;
    private String biteCode;
}
