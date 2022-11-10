package ru.team2.skud.event;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.team2.skud.mapper.MapperConfig;
import ru.team2.skud.event.dto.NewEventDto;

import java.util.Date;

@Mapper(componentModel = "spring", config = MapperConfig.class)
public abstract class EventMapper {

    public abstract Event newEventDtoToEvent(NewEventDto newEventDto);

    @AfterMapping
    public void afterMapping(@MappingTarget Event event, NewEventDto newEventDto) {
        event.setDate(new Date().getTime());
        event.setNew(true);
    }
}