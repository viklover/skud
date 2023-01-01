package ru.team2.skud.sms;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.team2.skud.session.request.NewMessage;
import ru.team2.skud.session.request.ResponseMessage;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.reactive.function.server.ServerResponse.status;

@Component
@RequiredArgsConstructor
public class SmsHandler {

    private SmsService smsService;

    public Mono<ServerResponse> processMessage(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(NewMessage.class)
                .flatMap(message -> status(CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(smsService.newMessage(message), ResponseMessage.class));
    }

    public Mono<ServerResponse> sendSavedNotifications(ServerRequest serverRequest) {
        smsService.sendSavedNotifications();
        return ServerResponse.ok().build();
    }
}
