package ru.team2.skud.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class NewEventResource {

    @JsonProperty("card_id")
    private Long cardId;

    @JsonProperty("event_type")
    private EventType eventType;
}