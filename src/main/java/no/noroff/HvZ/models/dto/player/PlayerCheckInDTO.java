package no.noroff.HvZ.models.dto.player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerCheckInDTO {
    private String lastCheckInTime;
    private double lat;
    private double lng;
}
