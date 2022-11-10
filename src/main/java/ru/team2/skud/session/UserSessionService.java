package ru.team2.skud.session;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.persons.parent.ParentService;
import ru.team2.skud.session.config.SessionConfig;
import ru.team2.skud.session.config.SessionConfigRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserSessionService {

    private final UserSessionRepository userSessionRepository;

    private final SessionConfigRepository sessionConfigRepository;
    private final ParentService parentService;

    public Mono<UserSession> create(UserSession session) {
        return Mono.just(session)
                .zipWith(sessionConfigRepository.save(new SessionConfig()))
                .map(tuple -> tuple.getT1().setSessionConfigId(tuple.getT2().getId()))
                .flatMap(userSessionRepository::save);
    }

    public Flux<UserSession> findUserSessionByListParentIds(List<Long> parentIdsList) {
        return userSessionRepository.findUserSessionByParentIdIn(parentIdsList);
    }

    public Mono<Boolean> existsSessionByPlatformAndSessionId(PlatformType platform, Long id) {
        return userSessionRepository.existsUserSessionByPlatformAndSessionId(platform, id);
    }

    public Mono<Boolean> existsUserSessionByParentId(Long parentId) {
        return userSessionRepository.existsUserSessionByParentId(parentId);
    }

    public Mono<Boolean> existsSessionByTelephoneNumber(String telephoneNumber) {
        return parentService.findParentByTelephoneNumber(telephoneNumber)
                .filter(Objects::nonNull)
                .flatMap(i -> userSessionRepository.findUserSessionByParentId(i.getId()))
                .map(Objects::nonNull)
                .switchIfEmpty(Mono.just(Boolean.FALSE));
//        return userSessionRepository.existsUserSessionByTelephoneNumber(telephoneNumber);
    }
}
