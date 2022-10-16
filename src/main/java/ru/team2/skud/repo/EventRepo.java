package ru.team2.skud.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import ru.team2.skud.domain.Event;

@Repository
public interface EventRepo extends ReactiveCrudRepository<Event, Long> {
}
