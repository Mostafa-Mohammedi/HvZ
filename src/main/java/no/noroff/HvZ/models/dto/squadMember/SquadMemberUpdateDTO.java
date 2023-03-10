package no.noroff.HvZ.models.dto.squadMember;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class SquadMemberUpdateDTO {
    private Integer id;
    private String rank;

}
