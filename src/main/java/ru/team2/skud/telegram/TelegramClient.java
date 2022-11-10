package ru.team2.skud.telegram;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.team2.skud.session.SessionService;

@Service
public class TelegramClient {

    @Qualifier("telegram-webclient")
    private WebClient webClient;
    private SessionService sessionService;


}
