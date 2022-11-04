package ru.team2.skud.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.*;
import ru.team2.skud.event.dto.NewEventDto;

@RestController
@CrossOrigin
@RequestMapping("/events")
@RequiredArgsConstructor
@Slf4j
public class EventsController {

    private final EventService eventService;

    @PostMapping
    public Mono<Event> create(final @RequestBody NewEventDto event) {
        return eventService.create(event);
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
