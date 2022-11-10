package ru.team2.skud.event;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.team2.skud.event.dto.NewEventDto;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.reactive.function.server.ServerResponse.status;

@Component
@RequiredArgsConstructor
public class EventHandler {

    private final EventService eventService;

    public Mono<ServerResponse> createEvent(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(NewEventDto.class)
                .flatMap(event -> status(CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(eventService.create(event), Event.class));
    }

    public Mono<ServerResponse> findAllEvents(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(eventService.findAll(), Event.class)
                .switchIfEmpty(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> findEventById(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(eventService.findById(Long.valueOf(serverRequest.pathVariable("id"))), Event.class);
    }
}
