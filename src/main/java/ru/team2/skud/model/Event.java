package ru.team2.skud.model;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Table("event")
public class Event {

    @Id
    @JsonIgnore
    private Long id;

    @JsonProperty("card_id")
    private Long cardId;

    private Long date;

    @JsonProperty("event_type")
    private EventType eventType;

    @Transient
    private Student student;
}
