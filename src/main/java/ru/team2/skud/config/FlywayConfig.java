package ru.team2.skud.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:configs/database.properties")
public class FlywayConfig {

    private final DatabaseConfig config;

    public FlywayConfig(DatabaseConfig config) {
        this.config = config;
    }

    @Bean
    public void initOptions() {
        Flyway.configure().dataSource(config.jdbcUrl, config.user, config.password).load().migrate();
    }

}
