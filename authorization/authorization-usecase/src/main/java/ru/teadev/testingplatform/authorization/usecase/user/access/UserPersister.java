package ru.teadev.testingplatform.authorization.usecase.user.access;

import ru.teadev.testingplatform.authorization.domain.user.User;

public interface UserPersister {

    User save(User user);

}
