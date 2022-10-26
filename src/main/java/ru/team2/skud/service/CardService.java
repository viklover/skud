package ru.team2.skud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.model.Card;
import ru.team2.skud.repository.CardRepository;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepo;

    public Flux<Card> findAll() {
        return cardRepo.findAll();
    }

    public Mono<Card> create(Card card) {
        return cardRepo.save(card);
    }

    public Mono<Card> findById(Long id) {
        return cardRepo.findById(id);
    }
}
