package ru.teadev.testingplatform.authorization.usecase.user;

import lombok.NonNull;
import ru.teadev.testingplatform.authorization.domain.user.Role;
import ru.teadev.testingplatform.authorization.domain.user.User;
import ru.teadev.testingplatform.authorization.domain.user.exception.UserNotFoundException;

import java.util.Set;

public interface UpdateUser {

    User execute(@NonNull User user,
                 @NonNull Set<Role> roles) throws UserNotFoundException;

}
