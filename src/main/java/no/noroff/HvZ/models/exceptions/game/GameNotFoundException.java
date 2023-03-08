package no.noroff.HvZ.models.exceptions.game;

import no.noroff.HvZ.models.exceptions.EntityNotFoundException;

public class GameNotFoundException extends EntityNotFoundException {
    public GameNotFoundException(int id) {
        super("Game with this id does not exist. ID: " + id);
    }
}

