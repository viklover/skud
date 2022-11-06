package ru.team2.skud.parent;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ParentRepository extends ReactiveSortingRepository<Parent, Long> {

    Mono<Parent> findParentByTelephoneNumber(String telephoneNumber);
    Mono<Boolean> existsParentByTelephoneNumber(String telephoneNumber);
}
