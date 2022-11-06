package ru.team2.skud.notify.session;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NewMessage {
    @JsonProperty("session_id")
    private Long sessionId;
    private String content;
    private PlatformType platform;
}
