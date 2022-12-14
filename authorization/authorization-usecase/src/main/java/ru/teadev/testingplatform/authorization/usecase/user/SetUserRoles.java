package ru.teadev.testingplatform.authorization.usecase.user;

import java.util.Set;

import lombok.NonNull;
import ru.teadev.testingplatform.authorization.domain.user.Role;
import ru.teadev.testingplatform.authorization.domain.user.User;
import ru.teadev.testingplatform.authorization.domain.user.UserId;
import ru.teadev.testingplatform.authorization.domain.user.exception.UserNotExistsException;

public interface SetUserRoles {

    User execute(@NonNull UserId userId,
                 @NonNull Set<Role> roles) throws UserNotExistsException;

}
