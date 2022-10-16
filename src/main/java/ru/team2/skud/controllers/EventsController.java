package ru.team2.skud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.domain.Event;
import ru.team2.skud.service.EventService;

@RestController
@RequestMapping("/events")
public class EventsController {

    private final EventService eventService;

    @Autowired
    public EventsController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public Mono<Event> addEvent(@RequestBody Event event) {
        return eventService.add(event);
    }

    @GetMapping
    public Flux<Event> getEvents() {
        return eventService.list();
    }

    @GetMapping("/{id}")
    public Mono<Event> getEvent(@PathVariable("id") Long id) {
        return eventService.getById(id);
    }
}
