package ru.teadev.testingplatform.authorization.usecase.user;

import lombok.NonNull;
import ru.teadev.testingplatform.authorization.domain.user.UserId;
import ru.teadev.testingplatform.authorization.domain.user.exception.UserNotExistsException;

public interface DeleteUser {

    void execute(@NonNull UserId userId) throws UserNotExistsException;

}
