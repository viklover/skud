package ru.team2.skud.event;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.team2.skud.event.dto.NewEventDto;
import ru.team2.skud.student.StudentMapper;

import java.util.Date;

@Mapper(componentModel = "spring", uses = {StudentMapper.class})
public abstract class EventMapper {

    public abstract Event toResource(Event item);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cardId")
    @Mapping(target = "eventType")
    public abstract Event toModel(NewEventDto item);

    @AfterMapping
    public void afterMapping(NewEventDto item, @MappingTarget Event event) {
        event.setDate(new Date().getTime());
    }
}