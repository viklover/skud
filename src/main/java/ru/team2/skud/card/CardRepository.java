package ru.team2.skud.card;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends ReactiveCrudRepository<Card, Long>, ReactiveSortingRepository<Card, Long> {
}