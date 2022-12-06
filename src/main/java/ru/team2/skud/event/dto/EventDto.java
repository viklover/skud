package ru.team2.skud.event.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.relational.core.mapping.Table;
import ru.team2.skud.event.EventType;

@Data
@Table("event")
public class EventDto {

    private Long id;

    private Long timestamp;

    private String date;

    @JsonProperty("event_type")
    private EventType eventType;

    @JsonProperty("student_id")
    private String studentId;
}
