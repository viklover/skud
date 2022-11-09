package ru.team2.skud.session.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.event.Event;
import ru.team2.skud.persons.parent.ParentService;
import ru.team2.skud.session.UserSessionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;


@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final ParentService parentService;

    public void createNotificationsFromEvent(Event event) {
        List<Notification> notificationsList = new ArrayList<Notification>();

        // TODO: CREATING NOTIFICATIONS

        for (Notification notification : notificationsList)
            notificationRepository.save(notification).subscribe();
    }
}
