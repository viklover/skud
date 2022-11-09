package ru.team2.skud.event;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.base.service.BaseEntityService;
import ru.team2.skud.event.dto.NewEventDto;
import ru.team2.skud.event.dto.UpdateEventDto;
import ru.team2.skud.persons.student.StudentService;
import ru.team2.skud.session.notification.NotificationService;

@Service
public class EventService extends BaseEntityService<Long, Event, EventRepository, EventMapper, NewEventDto, UpdateEventDto> {

    private final StudentService studentService;

    private final NotificationService notificationService;

    public EventService(EventRepository repository, EventMapper mapper, StudentService studentService, NotificationService notificationService) {
        super(repository, mapper);
        this.studentService = studentService;
        this.notificationService = notificationService;
    }

    @Override
    public Mono<Event> create(NewEventDto newEventDto) {
        return super.create(newEventDto).flatMap(this::loadRelations);
    }

    @Override
    public Flux<Event> findAll() {
        return super.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    protected Mono<Event> loadRelations(final Event event) {
        return super.loadRelations(event)
                .flatMap(this::loadStudentRelation)
                .flatMap(this::loadStudentDtoRelation);
    }

    private Mono<Event> loadStudentRelation(final Event event) {
        return Mono.just(event)
                .zipWith(studentService.findStudentByCardId(event.getCardId()))
                .map(result -> result.getT1().setStudent(result.getT2()));
    }

    private Mono<Event> loadStudentDtoRelation(final Event event) {
        return Mono.just(event)
                .map(entity -> entity.setStudentDto(
                        studentService.mapper.studentToStudentForEventDto(entity.getStudent())));
    }
}
