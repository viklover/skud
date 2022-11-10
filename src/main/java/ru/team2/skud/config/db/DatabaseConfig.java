package ru.team2.skud.config.db;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;
import static io.r2dbc.spi.ConnectionFactoryOptions.PORT;

@Configuration
@PropertySource("classpath:configs/database.properties")
public class DatabaseConfig {

    private static final Log log = LogFactory.getLog(DatabaseConfig.class);
    @Value("${user}") public String user;
    @Value("${password}") public String password;
    @Value("${host}") public String host;
    @Value("${port}") public int port;
    @Value("${database}") public String database;
    @Value("${driver}") public String driver;

    public String jdbcUrl;

    @Bean
    public void initOptions() {
        final String jdbcUrl = String.format("jdbc:%s://%s:%s/%s", driver, host, port, database);
        Flyway.configure().dataSource(jdbcUrl, user, password).load().migrate();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(
                ConnectionFactoryOptions.builder()
                        .option(DRIVER, driver)
                        .option(HOST, host)
                        .option(USER, user)
                        .option(PASSWORD, password)
                        .option(DATABASE, database)
                        .option(PORT, port)
                        .build());
    }
}
