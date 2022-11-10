package ru.team2.skud.session.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.event.Event;
import ru.team2.skud.persons.parent.ParentService;
import ru.team2.skud.session.Session;
import ru.team2.skud.session.SessionService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationFactory notificationFactory;
    private final SessionService userSessionService;
    private final ParentService parentService;

    public void initNotifications(Event event) {
        List<Notification> notificationsList = new ArrayList<Notification>();

        Flux<Session> userSessions = userSessionService.findUserSessionByListParentIds(
                event.getStudent().getParents().stream().map(parent -> parent.getId()).collect(Collectors.toList()));

        userSessions.doOnEach(sessions -> {
            Mono.fromSupplier(sessions).doOnNext(session -> {
                notificationsList.add(NotificationFactory.create(event, session));
            }).subscribe();
        }).subscribe();

        // TODO: SAVE NOTIFICAITONS

//        for (Notification notification : notificationsList)
//            notificationRepository.save(notification).subscribe();
    }
}
