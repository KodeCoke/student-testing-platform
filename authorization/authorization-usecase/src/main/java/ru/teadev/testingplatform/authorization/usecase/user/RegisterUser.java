package ru.teadev.testingplatform.authorization.usecase.user;

import java.util.Set;

import lombok.NonNull;
import ru.teadev.testingplatform.authorization.domain.user.Role;
import ru.teadev.testingplatform.authorization.domain.user.User;
import ru.teadev.testingplatform.authorization.domain.user.exception.BlankLoginException;
import ru.teadev.testingplatform.authorization.domain.user.exception.BlankPasswordException;
import ru.teadev.testingplatform.authorization.domain.user.exception.LoginAlreadyExistsException;

public interface RegisterUser {

    User execute(@NonNull String login,
                 @NonNull String password,
                 @NonNull Set<Role> roles) throws BlankPasswordException, LoginAlreadyExistsException, BlankLoginException;
}
