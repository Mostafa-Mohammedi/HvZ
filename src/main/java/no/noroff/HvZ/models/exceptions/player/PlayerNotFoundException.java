package no.noroff.HvZ.models.exceptions.player;

import no.noroff.HvZ.models.exceptions.EntityNotFoundException;

public class PlayerNotFoundException extends EntityNotFoundException{
    public PlayerNotFoundException(int id) {
        super("Player with this id does not exist. ID: " + id);
    }
}

