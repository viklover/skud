package ru.team2.skud.student;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@CacheConfig(cacheNames = {"students", "student_by_card"})
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public Mono<Student> create(NewStudentResource student) {
        return studentRepository.save(studentMapper.toModel(student).setNew(true));
    }

    @CachePut(key = "#student.id")
    @CacheEvict(value = "student_by_card", key = "#student.cardId")
    public Mono<Student> update(NewStudentResource student) {
        return studentRepository.save(studentMapper.toModel(student)).cache();
    }

    public Flux<Student> findAll() {
        return studentRepository.findAll();
    }

    @Cacheable
    public Mono<Student> findById(String id) {
        return studentRepository.findById(id).cache();
    }

    @Cacheable(value = "student_by_card", key = "#id")
    public Mono<Student> findStudentByCardId(Long id) {
        return studentRepository.findStudentByCardId(id).cache();
    }
}
