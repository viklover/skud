package ru.team2.skud.session;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Getter
@Setter
@Accessors(chain = true)
public class NotVerifiedUserSession {

    private PlatformType platform;
    private int registrationCode;
    private Long sessionId;
    private String telephoneNumber;
}
