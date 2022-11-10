package ru.team2.skud.telegram;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TelegramHandler {

    public Mono<ServerResponse> processMessage(ServerRequest serverRequest) {
        return ServerResponse.ok().build();
    }
}
