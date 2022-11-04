package ru.team2.skud.student;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.base.service.BaseEntityService;
import ru.team2.skud.db.relations.StudentsParentsRepository;
import ru.team2.skud.parent.ParentService;
import ru.team2.skud.student.dto.NewStudentDto;
import ru.team2.skud.student.dto.UpdateStudentDto;

@Service
public class StudentService extends BaseEntityService<String, Student, StudentRepository, StudentMapper, NewStudentDto, UpdateStudentDto> {

    private final StudentsParentsRepository studentsParentsRepository;
    private final ParentService parentService;

    public StudentService(StudentRepository repository, StudentMapper mapper,
                          StudentsParentsRepository studentsParentsRepository, ParentService parentService) {
        super(repository, mapper);
        this.studentsParentsRepository = studentsParentsRepository;
        this.parentService = parentService;
    }

    public Mono<Student> findStudentByCardId(Long id) {
        return repository.findStudentByCardId(id)
                .flatMap(this::loadRelations);
    }

    public Flux<Student> findStudentsByParentId(Long id) {
        return studentsParentsRepository.findStudentsByParentId(id);
    }

    @Override
    protected Mono<Student> loadRelations(final Student student) {
        return super.loadRelations(student)
                .zipWith(parentService.findParentsByStudentId(student.getId()).collectList())
                .map(result -> result.getT1().setParents(result.getT2()));
    }
}
