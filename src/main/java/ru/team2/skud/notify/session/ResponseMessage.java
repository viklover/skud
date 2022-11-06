package ru.team2.skud.notify.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Data
@AllArgsConstructor
public class ResponseMessage {

    public String content;

    public static Mono<ResponseMessage> create(String content) {
        return Mono.just(new ResponseMessage(content));
    }
}