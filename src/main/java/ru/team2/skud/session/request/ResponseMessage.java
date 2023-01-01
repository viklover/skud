package ru.team2.skud.session.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import reactor.core.publisher.Mono;

@Data
@AllArgsConstructor
public class ResponseMessage {

    public String content;

    public static Mono<ResponseMessage> create(String content) {
        return Mono.just(new ResponseMessage(content));
    }
}