package ru.team2.skud.notification.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.team2.skud.event.Event;
import ru.team2.skud.persons.parent.Parent;
import ru.team2.skud.session.Session;

@Data
@AllArgsConstructor
public class NotificationEventDao {
    public Event event;
    public Parent parent;
    public Session session;
}
