package ru.team2.skud.domain;

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
    private Long card_id;
    private Long date;
    private EventType type;
}
