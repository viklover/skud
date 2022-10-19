package ru.team2.skud.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Table("event")
public class Event {
    @Id
    private Long id;
    @JsonProperty("card_id")
    private Long cardId;
    private Long date;
    @JsonProperty("event_type")
    private EventType eventType;
}
