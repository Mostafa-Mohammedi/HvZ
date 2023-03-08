package no.noroff.HvZ.models.exceptions.squad;

import no.noroff.HvZ.models.exceptions.EntityNotFoundException;

public class SquadNotFoundException extends EntityNotFoundException {
    public SquadNotFoundException(int id) {
        super("Squad with this id does not exist. ID: " + id);
    }


}
