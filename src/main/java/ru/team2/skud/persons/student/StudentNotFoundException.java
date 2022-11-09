package ru.team2.skud.persons.student;

import ru.team2.skud.exception.NotFoundException;

public class StudentNotFoundException extends NotFoundException {

    public StudentNotFoundException(String id) {
        super(String.format("Student [%s] is not found", id));
    }
}
