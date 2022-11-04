package ru.team2.skud.card;

import org.mapstruct.Mapper;
import ru.team2.skud.base.mapper.BaseMapper;
import ru.team2.skud.base.mapper.BaseMapperConfig;
import ru.team2.skud.card.dto.NewCardDto;
import ru.team2.skud.card.dto.UpdateCardDto;

@Mapper(componentModel = "spring", config = BaseMapperConfig.class)
public abstract class CardMapper extends BaseMapper<Long, Card, NewCardDto, UpdateCardDto> {
}