package ru.team2.skud.event;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.event.dto.NewEventDto;
import ru.team2.skud.student.StudentService;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final StudentService studentService;

    public Mono<Event> create(NewEventDto event) {
        return eventRepository.save(eventMapper.toModel(event))
                .map(eventMapper::toResource)
                .flatMap(this::loadRelations);
    }

    public Flux<Event> findAll() {
        return eventRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
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
                .map(result -> result.getT1().setStudent(result.getT2()));
    }
}
