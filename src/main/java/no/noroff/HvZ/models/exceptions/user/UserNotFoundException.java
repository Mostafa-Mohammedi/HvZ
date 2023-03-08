package no.noroff.HvZ.models.exceptions.user;

import jakarta.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(int id) {
        super("User with the following id doesn't exist: " + id);
    }
}
