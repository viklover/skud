package ru.team2.skud.session.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.team2.skud.event.Event;
import ru.team2.skud.session.UserSession;
import ru.team2.skud.session.UserSessionService;

@Service
public class NotificationFactory {

    private UserSessionService userSessionService;

    public static Notification create(Event event, UserSession session) {

        // TODO: CREATING NOTIFICATIONS

        return new Notification();
    }


//    public static Notification fromEvent(Event event, UserSession userSession) {
//
//    }
}
