package ru.teadev.testingplatform.authorization.domain.session;

import java.util.UUID;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ru.teadev.testingplatform.authorization.domain.user.UserId;

@Service
public class SessionRestorer {

    @NonNull
    public Session restore(@NonNull UUID id,
                           @NonNull UserId userId) {
        return new Session(id, userId);
    }

}
