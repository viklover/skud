package ru.team2.skud.notification;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.team2.skud.session.platform.PlatformType;

@Repository
public interface NotificationRepository extends ReactiveCrudRepository<Notification, Long> {

    @Query("select * from notification where user_session_id in (select id from user_session where platform=?)")
    Flux<Notification> findAllByPlatformType(PlatformType platformType);
}
