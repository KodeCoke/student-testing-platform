package ru.teadev.testingplatform.authorization.usecase.user;

import lombok.NonNull;
import ru.teadev.testingplatform.authorization.domain.user.User;
import ru.teadev.testingplatform.authorization.domain.user.exception.UserNotFoundException;

public interface DeleteUser {

    void execute(@NonNull User user) throws UserNotFoundException;

}
