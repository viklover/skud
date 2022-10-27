package ru.team2.skud.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.team2.skud.model.Event;
import ru.team2.skud.rest.api.NewEventResource;

@Mapper(componentModel = "spring", uses = {StudentMapper.class})
public abstract class EventMapper {

    public abstract Event toResource(Event item);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cardId")
    @Mapping(target = "date")
    @Mapping(target = "eventType")
    public abstract Event toModel(NewEventResource item);
}