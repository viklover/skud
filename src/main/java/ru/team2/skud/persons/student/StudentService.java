package ru.team2.skud.persons.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.persons.StudentsParentsRepository;
import ru.team2.skud.persons.parent.ParentService;
import ru.team2.skud.persons.student.dto.NewStudentDto;
import ru.team2.skud.persons.student.dto.UpdateStudentDto;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    public final StudentMapper studentMapper;

    private final StudentsParentsRepository studentsParentsRepository;
    private final ParentService parentService;

    public Mono<Student> create(NewStudentDto newStudentDto) {
        return studentRepository.save(studentMapper.newStudentDtoToStudent(newStudentDto))
                .flatMap(this::loadRelations);
    }

    public Mono<Student> update(String id, UpdateStudentDto updateStudentDto) {
        return studentRepository.findById(id)
                .flatMap(student -> {
                    studentMapper.updateStudentDtoToStudent(student, updateStudentDto);
                    return studentRepository.save(student);
                })
                .flatMap(this::loadRelations);
    }

    public Flux<Student> findAll() {
        return studentRepository.findAll()
                .flatMap(this::loadRelations);
    }

    public Mono<Student> findById(String id) {
        return studentRepository.findById(id)
                .flatMap(this::loadRelations);
    }

    public Mono<Student> findStudentByCardId(Long id) {
        return studentRepository.findStudentByCardId(id)
                .flatMap(this::loadRelations);
    }

    private Mono<Student> loadRelations(final Student student) {
        return Mono.just(student)
                .zipWith(parentService.findParentsByStudentId(student.getId()).collectList())
                .map(result -> result.getT1().setParents(result.getT2()));
    }

    public Mono<Void> deleteById(String id) {
        return studentRepository.deleteById(id);
    }
}
