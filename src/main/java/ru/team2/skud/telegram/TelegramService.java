package ru.team2.skud.telegram;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.team2.skud.session.AuthUserService;
import ru.team2.skud.session.request.NewMessage;
import ru.team2.skud.session.platform.PlatformType;
import ru.team2.skud.session.request.ResponseMessage;
import ru.team2.skud.session.SessionService;

@Service
@RequiredArgsConstructor
@Slf4j
public class TelegramService {

    private final PlatformType PLATFORM = PlatformType.TELEGRAM;

    private final SessionService sessionService;

    private final AuthUserService authUserService;

    public Mono<ResponseMessage> newMessage(NewMessage message) {
        message.setPlatform(PLATFORM);
        return sessionService.existsSessionByPlatformAndSessionId(PLATFORM, message.getSessionId())
                .flatMap(isExists -> isExists ? processMessage(message) : authUserService.processAuth(message));
    }

    public Mono<ResponseMessage> processMessage(NewMessage message) {
        return Mono.just(new ResponseMessage("Ок"));
    }
}
