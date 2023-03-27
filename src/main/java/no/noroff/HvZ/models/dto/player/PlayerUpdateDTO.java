package no.noroff.HvZ.models.dto.player;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class PlayerUpdateDTO {
    private Integer id;
    private boolean isHuman;
    private int squadRef;

    private String username;

}
