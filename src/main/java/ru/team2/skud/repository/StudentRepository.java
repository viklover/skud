package ru.team2.skud.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.team2.skud.model.Student;

@Repository
public interface StudentRepository extends ReactiveCrudRepository<Student, Long> {

    @Query("select * from student where card_id=?")
    Mono<Student> findStudentByCardId(Long cardId);
}