package ru.team2.skud.event;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.event.dto.NewEventDto;
import ru.team2.skud.persons.student.StudentService;
import ru.team2.skud.session.notification.NotificationService;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    private final StudentService studentService;
    private final NotificationService notificationService;

    public Mono<Event> create(NewEventDto newEventDto) {
        return eventRepository.save(eventMapper.newEventDtoToEvent(newEventDto))
                .flatMap(this::loadStudentRelation)
                .flatMap(this::loadStudentDtoRelation)
                .doOnNext(notificationService::initNotifications);
    }

    public Mono<Event> findById(Long id) {
        return eventRepository.findById(id)
                .flatMap(this::loadStudentDtoRelation)
                .switchIfEmpty(Mono.error(new EventNotFoundException(id)));
    }

    public Flux<Event> findAll() {
        return eventRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .flatMap(this::loadStudentDtoRelation);
    }

    private Mono<Event> loadStudentRelation(final Event event) {
        return Mono.just(event)
                .zipWith(studentService.findStudentByCardId(event.getCardId()))
                .map(result -> result.getT1().setStudent(result.getT2()));
    }

    private Mono<Event> loadStudentDtoRelation(final Event event) {
        if (event.getStudent() != null)
            return Mono.just(event.setStudentDto(
                    studentService.studentMapper.studentToStudentForEventDto(event.getStudent())));

        return Mono.just(event)
                .zipWith(studentService.findStudentByCardId(event.getCardId()))
                .map(tuple -> tuple.getT1().setStudentDto(
                        studentService.studentMapper.studentToStudentForEventDto(tuple.getT2())));
    }
}
