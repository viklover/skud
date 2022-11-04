package ru.team2.skud.student;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface StudentRepository extends ReactiveSortingRepository<Student, String> {

    @Query("select * from student where card_id=?")
    Mono<Student> findStudentByCardId(Long cardId);
}