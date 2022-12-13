package ru.teadev.testingplatform.authorization.usecase.session.access;

import ru.teadev.testingplatform.authorization.domain.session.Session;

public interface SessionPersister {

    Session save(Session session);

}
