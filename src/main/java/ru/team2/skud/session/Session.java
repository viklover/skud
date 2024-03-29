package ru.team2.skud.session;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ru.team2.skud.persons.parent.Parent;
import ru.team2.skud.session.config.SessionConfig;
import ru.team2.skud.session.platform.PlatformType;

@Data
@Table("user_session")
@Accessors(chain = true)
public class Session {
    @Id
    private Long id;

    @JsonProperty("parent_id")
    private Long parentId;

    @JsonProperty("session_id")
    private Long sessionId;

    private PlatformType platform;

    @JsonIgnore
    @Column("config_id")
    private Long sessionConfigId;

    @Transient
    @JsonProperty("config")
    private SessionConfig sessionConfig;

    @Transient
    @JsonIgnore
    private Parent parent;
}
