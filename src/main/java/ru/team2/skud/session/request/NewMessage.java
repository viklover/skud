package ru.team2.skud.session.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.team2.skud.session.PlatformType;

@Data
public class NewMessage {
    @JsonProperty("session_id")
    private Long sessionId;
    private String content;
    private PlatformType platform;
}
