package no.noroff.HvZ.models.dto.kill;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KillDTO {
    private int id;
    private String time_of_death;
    private String story;
    private double lat;
    private double lng;

    private String playerName;
    private String squadName;
    private String biteCode;
}

