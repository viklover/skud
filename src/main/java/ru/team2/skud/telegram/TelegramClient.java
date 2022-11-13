package ru.team2.skud.telegram;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.team2.skud.session.SessionService;
import ru.team2.skud.session.notification.Notification;
import ru.team2.skud.telegram.response.Response;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class TelegramClient {

    @Qualifier("webClientTelegram")
    private final WebClient webClient;

    public Mono<Boolean> sendNotification(Notification notification) {
        return webClient.post()
                .uri("/event")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(notification.getContent()), String.class)
                .retrieve()
                .bodyToMono(Response.class)
                .map(response -> response.wasSent)
                .onErrorReturn(false);
    }
}
