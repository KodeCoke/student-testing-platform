package ru.teadev.testingplatform.authorization.domain.session;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ru.teadev.testingplatform.authorization.domain.user.UserId;

@Service
public class SessionRestorer {

    @NonNull
    public Session restore(@NonNull SessionId id,
                           @NonNull UserId userId) {
        return new Session(id, userId);
    }

}
