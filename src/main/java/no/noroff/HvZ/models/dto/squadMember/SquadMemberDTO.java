package no.noroff.HvZ.models.dto.squadMember;

import lombok.Data;
import no.noroff.HvZ.models.Player;

@Data
public class SquadMemberDTO {
    private Integer id;
    private String rank;
    private Player player;

}
