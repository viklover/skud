package ru.team2.skud.notify;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.team2.skud.notify.session.*;
import ru.team2.skud.parent.ParentService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUserService {

    private final UserSessionService userSessionService;
    private final UserSessionMapper mapper;
    private final Map<Long, NotVerifiedUserSession> newSessions = new HashMap<>();

    private final ParentService parentService;

    public Mono<ResponseMessage> processAuth(NewMessage message) {

        if (!newSessions.containsKey(message.getSessionId()))
            return createNotVerifiedUserSession(message);

        return checkRegistrationCode(message);
    }

    public Mono<ResponseMessage> createNotVerifiedUserSession(NewMessage message) {

        MessageParser parser = new MessageParser(message.getContent());

        if (!parser.hasCommands())
            return ResponseMessage.create("Меня надо активировать");

        Optional<String> result = parser.activateCommandParse();

        if (result.isEmpty())
            return ResponseMessage.create("Неверный формат номера");

        return parentService.existsParentByTelephoneNumber(result.get())
                .zipWith(userSessionService.existsSessionByTelephoneNumber(result.get()))
                .flatMap(tuple -> {
                    if (tuple.getT1() && !tuple.getT2()) {
                        NotVerifiedUserSession session = mapper.messageToNotVerifiedSession(message).setPlatform(message.getPlatform());
                        session.setTelephoneNumber(result.get());
                        newSessions.put(session.getSessionId(), session);

                        // TODO: Send Registration Code to Parent
                        System.out.printf("Registration Code: %d", session.getRegistrationCode());

                        return ResponseMessage.create("Подтвердите номер телефона через код подтверждения");
                    }
                    else if (tuple.getT2()) {
                        return ResponseMessage.create("Законный представитель с таким номером уже авторизован");
                    }
                    return ResponseMessage.create("Номер не найден в системе");
                });
    }

    public Mono<ResponseMessage> checkRegistrationCode(NewMessage message) {

        NotVerifiedUserSession session = newSessions.get(message.getSessionId());

        MessageParser parser = new MessageParser(message.getContent());
        Optional<Boolean> result = parser.compareRegistrationCodes(session.getRegistrationCode());

        if (result.get()) {
            newSessions.remove(message.getSessionId());
            return userSessionService.create(mapper.notVerifiedSessionToSession(session).setParentId(1L))
                    .zipWith(parentService.findParentByTelephoneNumber(session.getTelephoneNumber()))
                    .map(tuple -> tuple.getT1().setParentId(tuple.getT2().getId()))
                    .flatMap(i -> ResponseMessage.create("Вы авторизованы :)"));
        }

        return ResponseMessage.create("Код подтверждения неверный, попробуйте ещё раз");
    }
}
