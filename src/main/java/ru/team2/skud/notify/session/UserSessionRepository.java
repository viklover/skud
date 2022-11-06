package ru.team2.skud.notify.session;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserSessionRepository extends ReactiveSortingRepository<UserSession, Long> {

    Mono<UserSession> findUserSessionByParentId(Long parentId);

    Mono<Boolean> existsUserSessionByParentId(Long parentId);

    @Query("select exists(select * from user_session where parent_id in (select id from parent where telephone_number=?))")
    Mono<Boolean> existsUserSessionByTelephoneNumber(String telephoneNumber);

    Mono<Boolean> existsUserSessionByPlatformAndSessionId(PlatformType platform, Long sessionId);
}
