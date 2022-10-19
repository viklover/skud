package ru.team2.skud.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.team2.skud.domain.Event;
import ru.team2.skud.service.EventService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.reactive.function.server.ServerResponse.status;

@Component
public class EventHandler {

    private final EventService eventService;

    @Autowired
    public EventHandler(EventService eventService) {
        this.eventService = eventService;
    }

    public Mono<ServerResponse> createEvent(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Event.class)
                .flatMap(event -> status(CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(eventService.add(event), Event.class));
    }

    public Mono<ServerResponse> findAllEvents(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(eventService.list(), Event.class)
                .switchIfEmpty(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> findEventById(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(eventService.getById(Long.valueOf(serverRequest.pathVariable("id"))), Event.class);
    }
}
