package ru.team2.skud.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.team2.skud.model.Event;

@Repository
public interface EventRepository extends ReactiveCrudRepository<Event, Long> {
    Flux<Event> findAll(Sort sort);
}
