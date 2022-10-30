package ru.team2.skud.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.team2.skud.model.Event;
import ru.team2.skud.rest.api.NewEventResource;

import java.util.Date;

@Mapper(componentModel = "spring", uses = {StudentMapper.class})
public abstract class EventMapper {

    public abstract Event toResource(Event item);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cardId")
    @Mapping(target = "eventType")
    public abstract Event toModel(NewEventResource item);

    @AfterMapping
    public void afterMapping(NewEventResource item, @MappingTarget Event event) {
        event.setDate(new Date().getTime());
    }
}