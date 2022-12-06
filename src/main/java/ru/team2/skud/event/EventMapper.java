package ru.team2.skud.event;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.web.reactive.function.server.ServerRequest;
import ru.team2.skud.config.mapper.MapperConfig;
import ru.team2.skud.event.dto.EventDto;
import ru.team2.skud.event.dto.NewEventDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring", config = MapperConfig.class)
public abstract class EventMapper {

    public abstract Event eventDtoToEvent(EventDto newEventDto);
    public abstract Event newEventDtoToEvent(NewEventDto newEventDto);

    @AfterMapping
    public void afterMapping(@MappingTarget Event event, NewEventDto newEventDto) {

        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);

        event.setTimestamp(timestamp.getTime());
        event.setDate(now.format(DateTimeFormatter.ofPattern("ddMMyyyy")));
        event.setNew(true);
    }

    public EventDto requestToEventDto(ServerRequest serverRequest) {
        EventDto eventDto = new EventDto();

        serverRequest.queryParam("date").ifPresent(eventDto::setDate);
        serverRequest.queryParam("student_id").ifPresent(eventDto::setStudentId);
        serverRequest.queryParam("event_type").ifPresent(value -> eventDto.setEventType(EventType.valueOf(value)));

        System.out.println(eventDto.getStudentId());

        return eventDto;
    }
}