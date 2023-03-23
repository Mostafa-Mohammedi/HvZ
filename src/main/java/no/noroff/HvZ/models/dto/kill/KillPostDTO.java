package no.noroff.HvZ.models.dto.kill;

import lombok.Data;

@Data
public class KillPostDTO {
    private String time_of_death;
    private String story;
    private double lat;
    private double lng;
    private int playerRef;
    private int gameRef;
}
