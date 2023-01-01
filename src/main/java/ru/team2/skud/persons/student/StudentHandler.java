package ru.team2.skud.persons.student;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import ru.team2.skud.persons.Persons;
import ru.team2.skud.persons.PersonsService;
import ru.team2.skud.persons.student.dto.NewStudentDto;
import ru.team2.skud.persons.student.dto.UpdateStudentDto;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.reactive.function.server.ServerResponse.status;

@Component
@RequiredArgsConstructor
public class StudentHandler {

    private final StudentService studentService;
    private final PersonsService studentsParentsService;

    public Mono<ServerResponse> createStudent(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(NewStudentDto.class)
                .flatMap(student -> status(CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(studentService.create(student), Student.class));
    }

    public Mono<ServerResponse> updateStudentById(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UpdateStudentDto.class)
                .flatMap(student -> status(CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(studentService.update(
                                serverRequest.pathVariable("id"), student), Student.class));
    }

    public Mono<ServerResponse> addParentToStudentById(ServerRequest serverRequest) {

        if (serverRequest.queryParam("parent_id").isEmpty())
            return ServerResponse.notFound().build();

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(studentsParentsService.create(
                        (serverRequest.pathVariable("id")),
                        (Long.valueOf(serverRequest.queryParam("parent_id").get()))), Persons.class);
    }

    public Mono<ServerResponse> deleteParentToStudentById(ServerRequest serverRequest) {

        if (serverRequest.queryParam("parent_id").isEmpty())
            return ServerResponse.notFound().build();

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(studentsParentsService.deletePair(
                        (serverRequest.pathVariable("id")),
                        (Long.valueOf(serverRequest.queryParam("parent_id").get()))), Persons.class);
    }

    public Mono<ServerResponse> findAllStudents(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(studentService.findAll(), Student.class)
                .switchIfEmpty(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> findStudentById(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(studentService.findById(
                        (serverRequest.pathVariable("id"))), Student.class);
    }

    public Mono<ServerResponse> deleteStudentById(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(studentService.deleteById(
                        serverRequest.pathVariable("id")), Object.class);
    }
}
