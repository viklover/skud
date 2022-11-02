package ru.team2.skud.event;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends ReactiveSortingRepository<Event, Long> {
}