package no.noroff.HvZ.models.dto.user;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    //private Set<Integer> players;

}
