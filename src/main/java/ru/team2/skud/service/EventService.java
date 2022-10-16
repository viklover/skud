package ru.team2.skud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.domain.Event;
import ru.team2.skud.repo.EventRepo;

@Service
public class EventService {
    private final EventRepo eventRepo;

    @Autowired
    public EventService(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    public Flux<Event> list() {
        return eventRepo.findAll();
    }

    public Mono<Event> add(Event event) {
        return eventRepo.save(event);
    }

    public Mono<Event> getById(Long id) {
        return eventRepo.findById(id);
    }
}
