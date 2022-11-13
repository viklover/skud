package ru.team2.skud.event;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
@Repository
public interface EventRepository extends ReactiveSortingRepository<Event, Long> {

    @Query("select * from event where student_id=? order by date desc limit 1")
    Mono<Event> findLastEventByStudentId(String studentId);
}