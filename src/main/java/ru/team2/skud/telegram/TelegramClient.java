package ru.team2.skud.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Sinks;
import ru.team2.skud.session.notification.Notification;
import ru.team2.skud.session.UserSessionService;

@Service
public class TelegramClient {

    @Qualifier("telegram-webclient")
    private WebClient webClient;
    private UserSessionService sessionService;


}
