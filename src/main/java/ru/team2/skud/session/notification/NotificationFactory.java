package ru.team2.skud.session.notification;

import org.springframework.stereotype.Service;
import ru.team2.skud.event.Event;
import ru.team2.skud.session.Session;
import ru.team2.skud.session.SessionService;

@Service
public class NotificationFactory {

    private SessionService userSessionService;

    public static Notification create(Event event, Session session) {

        // TODO: CREATING NOTIFICATIONS

        return new Notification();
    }


//    public static Notification fromEvent(Event event, UserSession userSession) {
//
//    }
}
