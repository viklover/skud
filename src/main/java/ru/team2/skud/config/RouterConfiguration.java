package ru.team2.skud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.team2.skud.handlers.EventHandler;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@EnableWebFlux
public class RouterConfiguration {

    @Bean
    public RouterFunction<ServerResponse> routerEvents(EventHandler eventsHandler) {
        return route()
            .POST("/events", eventsHandler::createEvent)
            .GET("/events", eventsHandler::findAllEvents)
            .GET("/events/{id}", eventsHandler::findEventById)
            .build();
    }
}
