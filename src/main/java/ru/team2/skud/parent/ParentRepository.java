package ru.team2.skud.parent;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends ReactiveSortingRepository<Parent, Long> {
}
