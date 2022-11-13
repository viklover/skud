package ru.team2.skud.session.notification;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.team2.skud.event.Event;
import ru.team2.skud.session.Session;
import ru.team2.skud.session.SessionService;
import ru.team2.skud.session.notification.dao.NotificationEventDao;

@Service
public class NotificationFactory {

    private SessionService userSessionService;

    @SneakyThrows
    public static Notification create(Event event, Session session) {

        ObjectMapper objectMapper = new ObjectMapper();
        String eventJson = objectMapper.writeValueAsString(new NotificationEventDao(event, session.getParent(), session));

        Notification notification = new Notification();
        notification.setContent(eventJson);
        notification.setType(NotificationType.EVENT);
        notification.setUserSessionId(session.getId());
        notification.setUserSession(session);

        return notification;
    }
}
