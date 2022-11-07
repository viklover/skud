package ru.team2.skud.event;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;
import ru.team2.skud.base.api.PersistableImpl;
import ru.team2.skud.student.dto.StudentForEventDto;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Table("event")
public class Event extends PersistableImpl<Long> {

    @JsonProperty("card_id")
    private Long cardId;

    private Long date;

    @JsonProperty("event_type")
    private EventType eventType;

    @Transient
    private StudentForEventDto student;
}
