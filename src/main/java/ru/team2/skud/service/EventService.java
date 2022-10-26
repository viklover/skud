package ru.team2.skud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.model.Event;
import ru.team2.skud.mapper.EventMapper;
import ru.team2.skud.rest.api.NewEventResource;
import ru.team2.skud.exception.EventNotFoundException;
import ru.team2.skud.repository.EventRepository;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final StudentService studentService;
    private final CardService cardService;

    public Mono<Event> create(NewEventResource event) {
        return eventRepository.save(eventMapper.toModel(event))
                .map(eventMapper::toResource)
                .flatMap(this::loadRelations);
    }

    public Flux<Event> findAll() {
        return eventRepository.findAll()
                .map(eventMapper::toResource)
                .flatMap(this::loadRelations);
    }

    public Mono<Event> findById(final Long id) {
        return eventRepository.findById(id)
                .switchIfEmpty(Mono.error(new EventNotFoundException(id)))
                .flatMap(this::loadRelations);
    }

    private Mono<Event> loadRelations(final Event event) {
        return Mono.just(event)
                .zipWith(studentService.findStudentByCardId(event.getCardId()))
                .map(result -> result.getT1().setStudent(result.getT2()))
                .zipWith(cardService.findById(event.getCardId()))
                .map(result -> result.getT1().setCard(result.getT2()));
    }
}
