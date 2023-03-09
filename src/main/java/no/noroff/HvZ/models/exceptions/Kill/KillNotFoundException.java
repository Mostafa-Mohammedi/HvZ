package no.noroff.HvZ.models.exceptions.Kill;

import no.noroff.HvZ.models.exceptions.EntityNotFoundException;

public class KillNotFoundException extends EntityNotFoundException {
    public KillNotFoundException(int id) {
        super("Could not find kill by: " + id);
    }
}
