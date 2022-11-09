package ru.team2.skud.session.notification;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import ru.team2.skud.event.Event;
import ru.team2.skud.persons.parent.Parent;

@Data
@Table("user_notification")
public class Notification {
    @Id
    private Long id;

    private NotificationType type;

    private String content;

    @JsonProperty("was_sent")
    private boolean wasSent;

    private String payload;

    @JsonProperty("session_id")
    private Long userSessionId;

    public static Notification from(Event event, Parent parent) throws JsonProcessingException {
        Notification notification = new Notification();
        notification.setType(NotificationType.EVENT);
        notification.setContent(new ObjectMapper().writeValueAsString(event));
        return notification;
    }
}
