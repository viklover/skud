package ru.team2.skud.notification;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ru.team2.skud.session.Session;

@Data
@Table("notification")
public class Notification {
    @Id
    private Long id;

    private NotificationType type;

    private String content;

    private String payload;

    @JsonProperty("session_id")
    @Column("user_session_id")
    private Long userSessionId;

    @Transient
    @JsonIgnore
    private Session userSession;
}
