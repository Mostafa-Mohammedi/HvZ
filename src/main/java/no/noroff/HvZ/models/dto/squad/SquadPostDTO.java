package no.noroff.HvZ.models.dto.squad;

import lombok.Data;

@Data
public class SquadPostDTO {
    private String name;
    private int gameRef;

    private int[] playerIds;
}
