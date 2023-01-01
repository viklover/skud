package ru.team2.skud.telegram;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.team2.skud.notification.Notification;
import ru.team2.skud.model.response.Response;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class TelegramClient {

    @Qualifier("webClientTelegram")
    private final WebClient webClientTelegram;

    public Mono<Boolean> sendNotification(Notification notification) {
        return webClientTelegram.post()
                .uri("/event")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(notification.getContent()), String.class)
                .retrieve()
                .bodyToMono(Response.class)
                .map(response -> response.wasSent)
                .onErrorReturn(false);
    }
}
