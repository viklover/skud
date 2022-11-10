package ru.team2.skud.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;
import ru.team2.skud.model.PersistableImpl;
import ru.team2.skud.persons.student.Student;
import ru.team2.skud.persons.student.dto.StudentForEventDto;

@Data
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
    @JsonProperty("student")
    private StudentForEventDto studentDto;

    @Transient
    @JsonIgnore
    private Student student;
}
