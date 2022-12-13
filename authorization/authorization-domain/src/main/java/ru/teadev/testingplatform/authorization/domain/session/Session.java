package ru.teadev.testingplatform.authorization.domain.session;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.teadev.testingplatform.authorization.domain.user.UserId;

@Data
@Setter(AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Session {

    public static Session create(@NonNull UserId userId) {
        return new Session(new SessionId(UUID.randomUUID()), userId);
    }

    @NonNull
    private SessionId id;

    @NonNull
    private UserId userId;

}
