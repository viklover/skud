package ru.team2.skud.session.config;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionConfigRepository extends ReactiveCrudRepository<SessionConfig, Long> {
}
