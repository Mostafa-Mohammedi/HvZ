package no.noroff.HvZ.models.exceptions.game;

import noroff.accelerate.hvz.models.exceptions.EntityNotFoundException;

public class GameNotFoundException extends EntityNotFoundException {
    public GameNotFoundException(int id) {
        super("Character with this id does not exist. ID: " + id);
    }
}

