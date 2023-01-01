package ru.team2.skud.event;

import reactor.core.publisher.Mono;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.reactive.function.server.ServerResponse.status;

import ru.team2.skud.event.dto.NewEventDto;

@Component
@RequiredArgsConstructor
public class EventHandler {

    private final EventService eventService;
    private final EventMapper eventMapper;

    public Mono<ServerResponse> createEvent(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(NewEventDto.class)
                .flatMap(event -> status(CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(eventService.create(event), Event.class));
    }

    public Mono<ServerResponse> findAllEvents(ServerRequest serverRequest) {

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(eventService.findAllByExample(eventMapper.requestToEventDto(serverRequest)), Event.class);
    }

    public Mono<ServerResponse> findEventById(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(eventService.findById(Long.valueOf(serverRequest.pathVariable("id"))), Event.class);
    }
}
