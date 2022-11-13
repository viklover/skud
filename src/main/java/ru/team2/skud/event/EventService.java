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
        return Mono.just(newEventDto).zipWith(studentService.findStudentByCardId(newEventDto.getCardId()))
                .map(tuple -> eventMapper.newEventDtoToEvent(tuple.getT1()).setStudentId(tuple.getT2().getId()))
                .flatMap(eventRepository::save)
                .flatMap(this::loadStudent)
                .doOnNext(notificationService::initNotifications);
    }

    public Mono<Event> findById(Long id) {
        return eventRepository.findById(id)
                .flatMap(this::loadStudent)
                .switchIfEmpty(Mono.error(new EventNotFoundException(id)));
    }

    public Flux<Event> findAll() {
        return eventRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .flatMap(this::loadStudent);
    }

    private Mono<Event> loadStudent(final Event event) {
        return Mono.just(event)
                .zipWith(studentService.findById(event.getStudentId()))
                .map(tuple -> {
                    return tuple.getT1().setStudent(tuple.getT2()).setStudentDto(
                            studentService.studentMapper.studentToStudentForEventDto(event.getStudent())
                    );
                });
    }
}
