package ru.team2.skud.card;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public Flux<Card> findAll() {
        return cardRepository.findAll();
    }

    public Mono<Card> create(Card card) {
        return cardRepository.save(card);
    }

    public Mono<Card> findById(Long id) {
        return cardRepository.findById(id);
    }
}
