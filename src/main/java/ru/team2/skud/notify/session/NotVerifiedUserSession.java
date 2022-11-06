package ru.team2.skud.notify.session;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Getter
@Setter
@Accessors(chain = true)
public class NotVerifiedUserSession extends BaseSession {

    private PlatformType platform;
    private int registrationCode;
    private String telephoneNumber;
}
