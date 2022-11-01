package ru.team2.skud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.mapper.StudentMapper;
import ru.team2.skud.model.Student;
import ru.team2.skud.repository.StudentRepository;
import ru.team2.skud.rest.api.NewStudentResource;

@Service
@CacheConfig(cacheNames = "students")
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public Mono<Student> create(NewStudentResource student) {
        return studentRepository.save(studentMapper.toModel(student).setNew(true));
    }

    @CachePut(key = "#student.id")
    public Mono<Student> update(NewStudentResource student) {
        return studentRepository.save(studentMapper.toModel(student));
    }

    public Flux<Student> findAll() {
        return studentRepository.findAll();
    }

    @Cacheable
    public Mono<Student> findById(String id) {
        return studentRepository.findById(id);
    }

    public Mono<Student> findStudentByCardId(Long id) {
        return studentRepository.findStudentByCardId(id);
    }
}
