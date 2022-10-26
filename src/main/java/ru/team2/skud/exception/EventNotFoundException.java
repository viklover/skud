package ru.team2.skud.exception;

public class EventNotFoundException extends NotFoundException {

    public EventNotFoundException(Long id) {
        super(String.format("Event [%d] is not found", id));
    }
}
