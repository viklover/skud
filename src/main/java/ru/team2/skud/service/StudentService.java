package ru.team2.skud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.model.Student;
import ru.team2.skud.repository.StudentRepository;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Flux<Student> findAll() {
        return studentRepository.findAll();
    }

    public Mono<Student> create(Student student) {
        return studentRepository.save(student);
    }

    public Mono<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public Mono<Student> findStudentByCardId(Long id) {
        return studentRepository.findStudentByCardId(id);
    }
}
