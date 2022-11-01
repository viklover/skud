package ru.team2.skud.card;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping("/cards")
@RequiredArgsConstructor
@Slf4j
public class CardsController {

    private final CardService cardService;

    @GetMapping
    public Flux<Card> findAll() {
        return cardService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Card> findById(@PathVariable("id") Long id) {
        return cardService.findById(id);
    }
}
