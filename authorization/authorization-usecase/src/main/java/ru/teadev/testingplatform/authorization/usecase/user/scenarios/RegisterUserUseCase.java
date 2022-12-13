package ru.teadev.testingplatform.authorization.usecase.user.scenarios;

import java.util.Set;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.teadev.testingplatform.authorization.domain.user.LoginAlreadyExists;
import ru.teadev.testingplatform.authorization.domain.user.Role;
import ru.teadev.testingplatform.authorization.domain.user.User;
import ru.teadev.testingplatform.authorization.domain.user.exception.BlankLoginException;
import ru.teadev.testingplatform.authorization.domain.user.exception.BlankPasswordException;
import ru.teadev.testingplatform.authorization.domain.user.exception.LoginAlreadyExistsException;
import ru.teadev.testingplatform.authorization.usecase.user.RegisterUser;
import ru.teadev.testingplatform.authorization.usecase.user.access.UserPersister;

@Service
@RequiredArgsConstructor
public class RegisterUserUseCase implements RegisterUser {

    private final UserPersister userPersister;
    private final LoginAlreadyExists loginAlreadyExists;

    @Override
    public User execute(@NonNull String login,
                        @NonNull String password,
                        @NonNull Set<Role> roles) throws BlankPasswordException, LoginAlreadyExistsException, BlankLoginException {

        User user = User.create(login, password, loginAlreadyExists);

        user.addRoles(roles);

        return userPersister.save(user);
    }
}
