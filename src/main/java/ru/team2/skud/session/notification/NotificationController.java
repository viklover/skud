package ru.team2.skud.session.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.team2.skud.session.request.NewMessage;
import ru.team2.skud.session.request.ResponseMessage;
import ru.team2.skud.telegram.TelegramService;

@RestController
@CrossOrigin
@RequestMapping("/notify")
@Slf4j
@RequiredArgsConstructor
public class NotificationController {

    private final TelegramService telegramService;

    @PostMapping("/telegram")
    public Mono<ResponseMessage> processMessage(@RequestBody NewMessage message) {
        return telegramService.newMessage(message);
    }
}
