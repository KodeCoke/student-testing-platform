package ru.teadev.testingplatform.authorization.usecase.user.scenarios;

import java.util.Set;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.teadev.testingplatform.authorization.domain.user.Role;
import ru.teadev.testingplatform.authorization.domain.user.User;
import ru.teadev.testingplatform.authorization.domain.user.UserId;
import ru.teadev.testingplatform.authorization.domain.user.exception.UserNotExistsException;
import ru.teadev.testingplatform.authorization.usecase.user.SetUserRoles;
import ru.teadev.testingplatform.authorization.usecase.user.access.UserExtractor;
import ru.teadev.testingplatform.authorization.usecase.user.access.UserPersister;

@Service
@RequiredArgsConstructor
public class SetUserRolesUseCase implements SetUserRoles {

    private final UserExtractor userExtractor;
    private final UserPersister userPersister;

    @Override
    public User execute(@NonNull UserId userId,
                        @NonNull Set<Role> roles) throws UserNotExistsException {

        User user = userExtractor.findById(userId).orElse(null);

        if (user == null) {
            throw new UserNotExistsException();
        }

        user.clearRoles();
        user.addRoles(roles);

        return userPersister.save(user);
    }
}
