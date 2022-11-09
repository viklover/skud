package ru.team2.skud.session.config;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("session_config")
public class SessionConfig {
    @Id
    private Long id;
}
