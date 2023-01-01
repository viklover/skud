package ru.team2.skud.persons.parent;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.team2.skud.persons.parent.dto.NewParentDto;
import ru.team2.skud.persons.parent.dto.UpdateParentDto;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.reactive.function.server.ServerResponse.status;

@Component
@RequiredArgsConstructor
public class ParentHandler {
    private final ParentService parentService;

    public Mono<ServerResponse> createParent(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(NewParentDto.class)
                .flatMap(parent -> status(CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(parentService.create(parent), Parent.class));
    }

    public Mono<ServerResponse> updateParentById(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UpdateParentDto.class)
                .flatMap(parent -> status(CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(parentService.update(
                        Long.valueOf(serverRequest.pathVariable("id")), parent), Parent.class));
    }

    public Mono<ServerResponse> findAllParents(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(parentService.findAll(), Parent.class)
                .switchIfEmpty(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> findParentById(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(parentService.findById(
                    Long.valueOf(serverRequest.pathVariable("id"))), Parent.class);
    }

    public Mono<ServerResponse> deleteParentById(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(parentService.deleteById(
                    Long.valueOf(serverRequest.pathVariable("id"))), Object.class);
    }
}