package no.noroff.HvZ.models.exceptions.squadMember;

import no.noroff.HvZ.models.exceptions.EntityNotFoundException;

public class SquadMemberNotFound extends EntityNotFoundException {
    public SquadMemberNotFound(int id) {
        super("Could not find the squadMember with following id: " + id);
    }
}
