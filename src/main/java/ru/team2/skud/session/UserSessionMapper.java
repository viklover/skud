package ru.team2.skud.session;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.team2.skud.mapper.MapperConfig;
import ru.team2.skud.session.request.NewMessage;
import ru.team2.skud.session.request.ResponseMessage;

import java.util.Random;

@Mapper(componentModel = "spring", config = MapperConfig.class)
public abstract class UserSessionMapper {

    public abstract UserSession notVerifiedSessionToSession(NotVerifiedUserSession newEntityDto);
    public abstract NotVerifiedUserSession messageToNotVerifiedSession(NewMessage message);

    public abstract ResponseMessage userSessionToResponseMessage(UserSession userSession);

    @AfterMapping
    public void afterMapping(@MappingTarget NotVerifiedUserSession session, NewMessage message) {
        session.setRegistrationCode(new Random().nextInt((999999 - 111111) + 1) + 111111);
    }

    @AfterMapping
    public void afterMapping(@MappingTarget UserSession session, NotVerifiedUserSession newSession) {
        System.out.printf("%d: %d", session.getSessionId(), session.getSessionId());
    }
}