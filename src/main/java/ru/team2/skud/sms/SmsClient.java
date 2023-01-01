package ru.team2.skud.sms;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.team2.skud.notification.Notification;
import ru.team2.skud.model.response.Response;

@Service
@RequiredArgsConstructor
public class SmsClient {

    @Qualifier("webClientSms")
    private final WebClient webClientSms;

    public Mono<Boolean> sendNotification(Notification notification) {
        return webClientSms.post()
                .uri("/event")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(notification.getContent()), Integer.class)
                .retrieve()
                .bodyToMono(Response.class)
                .map(response -> response.wasSent)
                .onErrorReturn(false);
    }
}
