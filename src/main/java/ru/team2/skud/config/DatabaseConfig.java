package ru.team2.skud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:configs/database.properties")
public class DatabaseConfig {

    @Value("${user}")
    public String user;

    @Value("${password}")
    public String password;

    @Value("${host}")
    public String host;

    @Value("${port}")
    public int port;

    @Value("${database}")
    public String database;

    @Value("${driver}")
    public String driver;

    public String jdbcUrl;

    @Bean
    public void init() {
        jdbcUrl = String.format("jdbc:%s://%s:%s/%s",
                driver, host, port, database);
    }

}
