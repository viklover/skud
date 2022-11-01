package ru.team2.skud.event;

import ru.team2.skud.exception.NotFoundException;

public class EventNotFoundException extends NotFoundException {

    public EventNotFoundException(Long id) {
        super(String.format("Event [%d] is not found", id));
    }
}
