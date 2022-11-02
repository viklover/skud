package ru.team2.skud.student;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface StudentRepository extends ReactiveCrudRepository<Student, String> {

    @Query("select * from student where card_id=?")
    Mono<Student> findStudentByCardId(Long cardId);
}