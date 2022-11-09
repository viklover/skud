package ru.team2.skud.session;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.team2.skud.persons.parent.ParentService;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserSessionService {

    private final UserSessionRepository userSessionRepository;

    private final ParentService parentService;

    public Mono<UserSession> create(UserSession session) {
        return userSessionRepository.save(session);
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
