package ru.team2.skud.sms;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.team2.skud.notification.NotificationService;
import ru.team2.skud.session.platform.PlatformType;
import ru.team2.skud.session.request.NewMessage;
import ru.team2.skud.session.request.ResponseMessage;

@Service
@RequiredArgsConstructor
public class SmsService {

    private final PlatformType PLATFORM = PlatformType.SMS;

    private final SmsClient smsClient;

    private final NotificationService notificationService;

    public Mono<ResponseMessage> newMessage(NewMessage message) {
        return ResponseMessage.create("Test Response Message");
    }

    public void sendSavedNotifications() {
        notificationService.findAllByPlatformType(PLATFORM)
                .flatMap(notification -> Mono.just(notification).zipWith(
                        smsClient.sendNotification(notification))
                )
                .doOnEach(tuple -> {

                    if (tuple.get() == null)
                        return;

                    if (tuple.get().getT2()) {
                        notificationService.deleteById(tuple.get().getT1().getId()).subscribe();
                    }

                }).subscribe();
    }
}