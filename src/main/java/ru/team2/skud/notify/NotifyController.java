package ru.team2.skud.notify;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.team2.skud.notify.session.NewMessage;
import ru.team2.skud.notify.session.PlatformType;
import ru.team2.skud.notify.session.ResponseMessage;
import ru.team2.skud.notify.telegram.TelegramService;

@RestController
@CrossOrigin
@RequestMapping("/notify")
@Slf4j
@RequiredArgsConstructor
public class NotifyController {

    private final TelegramService telegramService;

    @PostMapping("/telegram")
    public Mono<ResponseMessage> processMessage(@RequestBody NewMessage message) {
        return telegramService.newMessage(message);
    }
}
