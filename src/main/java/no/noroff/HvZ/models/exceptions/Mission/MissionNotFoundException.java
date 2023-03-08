package no.noroff.HvZ.models.exceptions.Mission;

import no.noroff.HvZ.models.exceptions.EntityNotFoundException;

public class MissionNotFoundException extends EntityNotFoundException {
    public MissionNotFoundException(int id) {
        super("Could not find the mission by id: " + id);
    }
}
