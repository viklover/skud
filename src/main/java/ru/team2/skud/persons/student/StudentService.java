package ru.team2.skud.persons.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.event.EventRepository;
import ru.team2.skud.event.EventType;
import ru.team2.skud.persons.PersonsRepository;
import ru.team2.skud.persons.parent.ParentService;
import ru.team2.skud.persons.student.dto.NewStudentDto;
import ru.team2.skud.persons.student.dto.UpdateStudentDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    public final StudentMapper studentMapper;
    private final ParentService parentService;

    private final EventRepository eventRepository;

    public Mono<Student> create(NewStudentDto newStudentDto) {
        return studentRepository.save(studentMapper.newStudentDtoToStudent(newStudentDto));
    }

    public Mono<Student> update(String id, UpdateStudentDto updateStudentDto) {
        return studentRepository.findById(id)
                .flatMap(student -> {
                    studentMapper.updateStudentDtoToStudent(student, updateStudentDto);
                    return studentRepository.save(student);
                })
                .flatMap(this::loadParents);
    }

    public Flux<Student> findAll() {
        return studentRepository.findAll()
                .flatMap(this::loadParents)
                .flatMap(this::loadInCollegeField);
    }

    public Mono<Student> findById(String id) {
        return studentRepository.findById(id)
                .flatMap(this::loadParents);
    }

    public Mono<Student> findStudentByCardId(Long id) {
        return studentRepository.findStudentByCardId(id)
                .flatMap(this::loadParents);
    }

    private Mono<Student> loadParents(final Student student) {
        return Mono.just(student)
                .zipWith(parentService.findParentsByStudentId(student.getId()).collectList())
                .map(result -> result.getT1().setParents(result.getT2()));
    }

    private Mono<Student> loadInCollegeField(final Student student) {
        return Mono.just(student)
                .zipWith(
                        eventRepository.findLastEventByStudentId(student.getId())
                                .map(Optional::of)
                                .switchIfEmpty(Mono.just(Optional.empty())))
                .map(tuple -> {
                    if (tuple.getT2().isEmpty()) {
                        return tuple.getT1().setInCollege(false);
                    }
                    return tuple.getT1().setInCollege(tuple.getT2().get().getEventType() == EventType.ENTRANCE);
                });
    }

    public Mono<Void> deleteById(String id) {
        return studentRepository.deleteById(id);
    }
}
