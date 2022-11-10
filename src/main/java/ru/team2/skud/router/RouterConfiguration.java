package ru.team2.skud.router;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.team2.skud.event.EventHandler;
import ru.team2.skud.persons.parent.ParentHandler;
import ru.team2.skud.persons.student.StudentHandler;
import ru.team2.skud.telegram.TelegramHandler;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@EnableWebFlux
@RequiredArgsConstructor
public class RouterConfiguration {

    private final EventHandler eventsHandler;
    private final ParentHandler parentsHandler;
    private final StudentHandler studentHandler;
    private final TelegramHandler telegramHandler;

    @Bean
    public RouterFunction<ServerResponse> routeEvents() {
        return route()
                .POST("/events", eventsHandler::createEvent)
                .GET("/events", eventsHandler::findAllEvents)
                .GET("/events/{id}", eventsHandler::findEventById)
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> routeParents() {
        return route()
                .POST("/parents", parentsHandler::createParent)
                .GET("/parents", parentsHandler::findAllParents)
                .GET("/parents/{id}", parentsHandler::findParentById)
                .PUT("/parents/{id}", parentsHandler::updateParentById)
                .DELETE("/parents/{id}", parentsHandler::deleteParentById)
                .build();
    }


    @Bean
    public RouterFunction<ServerResponse> routeStudents() {
        return route()
                .POST("/students", studentHandler::createStudent)
                .GET("/students", studentHandler::findAllStudents)
                .GET("/students/{id}", studentHandler::findStudentById)
                .PUT("/students/{id}", studentHandler::updateStudentById)
                .DELETE("/students/{id}", studentHandler::deleteStudentById)
                .POST("/students/{id}/add_parent", studentHandler::addParentToStudentById)
                .DELETE("/students/{id}/delete_parent", studentHandler::deleteParentToStudentById)
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> routeUserSessionChannels() {
        return route()
                .POST("/notify/telegram", telegramHandler::processMessage)
                .build();
    }
}
