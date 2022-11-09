package ru.team2.skud.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Sinks;
import ru.team2.skud.event.dto.NewEventDto;
import ru.team2.skud.session.notification.NotificationService;

import java.util.concurrent.Flow;

@RestController
@CrossOrigin
@RequestMapping("/events")
@RequiredArgsConstructor
@Slf4j
public class EventsController {

    private final NotificationService notificationService;
    private final EventService eventService;

    @PostMapping
    public Mono<ServerResponse> create(final @RequestBody NewEventDto event) {
        eventService.create(event).subscribe(notificationService::createNotificationsFromEvent);
        return ServerResponse.ok().build();
    }

    @GetMapping
    public Flux<Event> findAll() {
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Event> findById(@PathVariable("id") Long id) {
        return eventService.findById(id);
    }
}
