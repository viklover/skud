package ru.team2.skud.student;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.db.relations.StudentsParentsRepository;
import ru.team2.skud.parent.ParentService;
import ru.team2.skud.student.dto.NewStudentDto;
import ru.team2.skud.student.dto.UpdateStudentDto;

@Service
@CacheConfig(cacheNames = {"students", "student_by_card"})
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    private final StudentsParentsRepository studentsParentsRepository;

    private final ParentService parentService;

    public Mono<Student> create(NewStudentDto student) {
        return studentRepository.save(studentMapper.newStudentDtoToStudent(student));
    }

    @CachePut(value = "students", key = "#id")
    @CacheEvict(value = "student_by_card")
    public Mono<Student> update(String id, UpdateStudentDto studentDto) {
        return studentRepository.findById(id).flatMap(student -> {
            studentMapper.updateStudentDtoToStudent(student, studentDto);
            return studentRepository.save(student);
        }).cache();
    }

    public Flux<Student> findAll() {
        return studentRepository.findAll()
                .flatMap(this::loadRelations)
                .cache();
    }

    @Cacheable(value = "students", key = "#id")
    public Mono<Student> findById(String id) {
        return studentRepository.findById(id)
                .flatMap(this::loadRelations)
                .cache();
    }

    @Cacheable(value = "student_by_card", key = "#id")
    public Mono<Student> findStudentByCardId(Long id) {
        return studentRepository.findStudentByCardId(id)
                .flatMap(this::loadRelations)
                .cache();
    }

    public Flux<Student> findStudentsByParentId(Long id) {
        return studentsParentsRepository.findStudentsByParentId(id);
    }

    private Mono<Student> loadRelations(final Student student) {
        return Mono.just(student)
                .zipWith(parentService.findParentsByStudentId(student.getId()).collectList())
                .map(result -> result.getT1().setParents(result.getT2()));
    }
}
