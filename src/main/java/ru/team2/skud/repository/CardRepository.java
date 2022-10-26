package ru.team2.skud.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import ru.team2.skud.model.Card;

@Repository
public interface CardRepository extends ReactiveCrudRepository<Card, Long> {
}