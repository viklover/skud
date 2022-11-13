package ru.team2.skud.session.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.event.Event;
import ru.team2.skud.model.PersistableImpl;
import ru.team2.skud.session.Session;
import ru.team2.skud.session.SessionService;
import ru.team2.skud.session.platform.PlatformType;
import ru.team2.skud.telegram.TelegramClient;

import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final SessionService userSessionService;

    private final TelegramClient telegramClient;

    public void initNotifications(Event event) {

        Flux<Session> userSessions = userSessionService.findUserSessionByListParentIds(event.getStudent()
                .getParents().stream().map(PersistableImpl::getId).collect(Collectors.toList()));

        userSessions.map(session ->
                NotificationFactory.create(event, session)).map(notification -> {

            sendNotification(notification).doOnNext(bool -> {
                if (!bool) {
                    notificationRepository.save(notification).subscribe();
                }
            }).subscribe();

            return notification;

        }).subscribe();
    }

    private Mono<Boolean> sendNotification(Notification notification) {

        switch (notification.getUserSession().getPlatform()) {
            case TELEGRAM:
                return telegramClient.sendNotification(notification);
        }

        return Mono.just(false);
    }

    public Flux<Notification> findAllByPlatformType(PlatformType platformType) {
        return notificationRepository.findAllByPlatformType(platformType);
    }

    public Mono<Void> deleteById(Long id) {
        return notificationRepository.deleteById(id);
    }
}
