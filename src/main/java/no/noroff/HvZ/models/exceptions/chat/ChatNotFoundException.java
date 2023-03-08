package no.noroff.HvZ.models.exceptions.chat;

import no.noroff.HvZ.models.exceptions.EntityNotFoundException;

public class ChatNotFoundException extends EntityNotFoundException {

    public ChatNotFoundException(int id) {
        super("could not find chat id: " + id);
    }
}
