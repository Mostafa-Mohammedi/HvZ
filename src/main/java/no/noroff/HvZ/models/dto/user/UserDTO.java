package no.noroff.HvZ.models.dto.user;

import lombok.Data;
import no.noroff.HvZ.models.User;

import java.util.Set;

@Data
public class UserDTO {
    private int id;
    private String username;
    private Set<Integer> players;

}
