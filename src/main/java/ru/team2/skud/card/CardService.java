package ru.team2.skud.card;

import org.springframework.stereotype.Service;
import ru.team2.skud.base.service.BaseEntityService;
import ru.team2.skud.card.dto.NewCardDto;
import ru.team2.skud.card.dto.UpdateCardDto;

@Service
public class CardService extends BaseEntityService<Long, Card, CardRepository, CardMapper, NewCardDto, UpdateCardDto> {

    public CardService(CardRepository repository, CardMapper mapper) {
        super(repository, mapper);
    }
}