package ru.team2.skud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.exception.StudentNotFoundException;
import ru.team2.skud.mapper.StudentMapper;
import ru.team2.skud.model.Student;
import ru.team2.skud.repository.CardRepository;
import ru.team2.skud.repository.StudentRepository;
import ru.team2.skud.rest.api.NewStudentResource;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    private final CardRepository cardRepository;

    public Mono<Student> create(NewStudentResource student) {
        return studentRepository.save(studentMapper.toModel(student));
    }

    public Flux<Student> findAll() {
        return studentRepository.findAll();
    }

    public Mono<Student> findById(String id) {
        return studentRepository.findById(id)
                .switchIfEmpty(Mono.error(new StudentNotFoundException(id)))
                .flatMap(this::loadRelations);
    }

    public Mono<Student> findStudentByCardId(Long id) {
        return studentRepository.findStudentByCardId(id);
    }

    private Mono<Student> loadRelations(final Student student) {
        return Mono.just(student)
                .zipWith(cardRepository.findById(student.getCardId()))
                .map(result -> result.getT1().setCard(result.getT2()));
    }
}
