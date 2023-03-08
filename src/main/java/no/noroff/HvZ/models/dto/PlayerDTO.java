package no.noroff.HvZ.models.dto;

import lombok.Getter;
import lombok.Setter;
import no.noroff.HvZ.models.Squad;

import java.util.Set;

@Getter
@Setter
public class PlayerDTO {
    private int id;
    private boolean isHuman;
    private boolean isPatientZero;
    private String biteCode;
    private Squad squad;
}
