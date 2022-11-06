package ru.team2.skud.notify.session;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("user_session")
@Accessors(chain = true)
public class UserSession extends BaseSession {
    @Id
    private Long id;

    @JsonProperty("parent_id")
    private Long parentId;

    private PlatformType platform;
}
