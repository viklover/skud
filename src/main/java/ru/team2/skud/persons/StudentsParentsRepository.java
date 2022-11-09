package ru.team2.skud.persons;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.persons.parent.Parent;
import ru.team2.skud.persons.student.Student;

@Repository
public interface StudentsParentsRepository extends ReactiveCrudRepository<StudentsParents, Long> {

    @Query("select * from students_parents where student_id=? and parent_id=?")
    Mono<StudentsParents> findPairByIds(String student_id, Long parent_id);

    @Query("select * from student where id in (select student_id from students_parents where parent_id=?)")
    Flux<Student> findStudentsByParentId(Long id);

    @Query("select * from parent where id in (select parent_id from students_parents where student_id=?)")
    Flux<Parent> findParentsByStudentId(String id);
}
