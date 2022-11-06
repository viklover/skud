package ru.team2.skud.notify;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.team2.skud.notify.session.UserSessionRepository;

@Service
@RequiredArgsConstructor
public class NotifyService {

    private final UserSessionRepository userSessionRepository;


}
