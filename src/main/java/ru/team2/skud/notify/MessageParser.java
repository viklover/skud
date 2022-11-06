package ru.team2.skud.notify;

import lombok.RequiredArgsConstructor;
import ru.team2.skud.notify.session.NewMessage;

import java.util.Optional;

public class MessageParser {

    private final String content;

    public MessageParser(String content) {
        this.content = content.strip();
    }

    public Boolean hasCommands() {
        return content.startsWith("/activate");
    }

    public Optional<String> activateCommandParse() {

        if (content.startsWith("/activate") && content.length() >= 20)
            return Optional.of(content.substring(10));

        return Optional.empty();
    }

    public Optional<Boolean> compareRegistrationCodes(int registrationCode) {
        return Optional.of(content.equals(String.valueOf(registrationCode)));
    }

}
