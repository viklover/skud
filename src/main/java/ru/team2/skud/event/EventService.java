package ru.team2.skud.event;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.base.service.BaseEntityService;
import ru.team2.skud.event.dto.NewEventDto;
import ru.team2.skud.event.dto.UpdateEventDto;
import ru.team2.skud.student.StudentService;

@Service
public class EventService extends BaseEntityService<Long, Event, EventRepository, EventMapper, NewEventDto, UpdateEventDto> {

    private final StudentService studentService;

    public EventService(EventRepository repository, EventMapper mapper, StudentService studentService) {
        super(repository, mapper);
        this.studentService = studentService;
    }

    @Override
    public Flux<Event> findAll() {
        return super.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    protected Mono<Event> loadRelations(final Event event) {
        return super.loadRelations(event)
                .zipWith(studentService.findStudentByCardId(event.getCardId()))
                .map(result -> result.getT1().setStudent(result.getT2()));
    }
}
