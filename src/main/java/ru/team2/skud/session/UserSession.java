package ru.team2.skud.session;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;
import ru.team2.skud.session.config.SessionConfig;

@Data
@Table("user_session")
@Accessors(chain = true)
public class UserSession {
    @Id
    private Long id;

    @JsonProperty("parent_id")
    private Long parentId;
    
    private Long sessionId;

    private PlatformType platform;

    @JsonIgnore
    private Long sessionConfigId;

    @Transient
    @JsonProperty("config")
    private SessionConfig sessionConfig;
}
