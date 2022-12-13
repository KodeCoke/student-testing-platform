package ru.teadev.testingplatform.authorization.usecase.session.access;

import java.util.Optional;

import lombok.NonNull;
import ru.teadev.testingplatform.authorization.domain.session.Session;
import ru.teadev.testingplatform.authorization.domain.session.SessionId;

public interface SessionExtractor {

    Optional<Session> findById(@NonNull SessionId id);
}
