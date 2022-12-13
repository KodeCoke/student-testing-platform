package ru.teadev.testingplatform.authorization.usecase.session.access;

import ru.teadev.testingplatform.authorization.domain.session.Session;

public interface SessionRemover {

    void delete(Session session);
}
