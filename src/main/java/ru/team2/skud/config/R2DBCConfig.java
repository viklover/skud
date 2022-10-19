package ru.team2.skud.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
@PropertySource("classpath:configs/database.properties")
public class R2DBCConfig {

    private final DatabaseConfig config;

    public R2DBCConfig(DatabaseConfig config) {
        this.config = config;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(
                ConnectionFactoryOptions.builder()
                        .option(DRIVER, config.driver)
                        .option(HOST, config.host)
                        .option(USER, config.user)
                        .option(PASSWORD, config.password)
                        .option(DATABASE, config.database)
                        .option(PORT, config.port)
                        .build());
    }
}
