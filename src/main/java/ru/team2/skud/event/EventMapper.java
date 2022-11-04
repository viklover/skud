package ru.team2.skud.event;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.team2.skud.base.mapper.BaseMapper;
import ru.team2.skud.base.mapper.BaseMapperConfig;
import ru.team2.skud.event.dto.NewEventDto;
import ru.team2.skud.event.dto.UpdateEventDto;

import java.util.Date;

@Mapper(componentModel = "spring", config = BaseMapperConfig.class)
public abstract class EventMapper extends BaseMapper<Long, Event, NewEventDto, UpdateEventDto> {

    @Override
    @AfterMapping
    public void afterMapping(@MappingTarget Event event, NewEventDto newEventDto) {
        super.afterMapping(event, newEventDto);
        event.setDate(new Date().getTime());
    }
}