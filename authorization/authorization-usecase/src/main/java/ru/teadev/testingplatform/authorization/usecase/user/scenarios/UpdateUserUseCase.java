package ru.teadev.testingplatform.authorization.usecase.user.scenarios;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.teadev.testingplatform.authorization.domain.user.Role;
import ru.teadev.testingplatform.authorization.domain.user.User;
import ru.teadev.testingplatform.authorization.domain.user.exception.UserNotFoundException;
import ru.teadev.testingplatform.authorization.usecase.user.UpdateUser;
import ru.teadev.testingplatform.authorization.usecase.user.access.UserExtractor;
import ru.teadev.testingplatform.authorization.usecase.user.access.UserPersister;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCase implements UpdateUser {

    private final UserExtractor userExtractor;
    private final UserPersister userPersister;

    @Override
    public User execute(@NonNull User user,
                        @NonNull Set<Role> roles) throws UserNotFoundException {

        if (userExtractor.findById(user.getId()).isEmpty()) {
            throw new UserNotFoundException();
        }

        user.getRoles().clear();
        user.addRoles(roles);

        return userPersister.save(user);
    }
}
