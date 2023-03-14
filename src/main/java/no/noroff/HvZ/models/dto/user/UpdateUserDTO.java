package no.noroff.HvZ.models.dto.user;

import lombok.Data;

import java.util.Set;

@Data
public class UpdateUserDTO {

    private Integer id;
    private String username;
    //private Set<Integer> players;
}

