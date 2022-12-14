package ru.teadev.testingplatform.authorization.usecase.user.scenarios;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.teadev.testingplatform.authorization.domain.user.User;
import ru.teadev.testingplatform.authorization.domain.user.UserId;
import ru.teadev.testingplatform.authorization.domain.user.exception.UserNotExistsException;
import ru.teadev.testingplatform.authorization.usecase.user.DeleteUser;
import ru.teadev.testingplatform.authorization.usecase.user.access.UserExtractor;
import ru.teadev.testingplatform.authorization.usecase.user.access.UserRemover;

@Service
@RequiredArgsConstructor
public class DeleteUserUseCase implements DeleteUser {

    private final UserExtractor userExtractor;
    private final UserRemover userRemover;

    @Override
    public void execute(@NonNull UserId userId) throws UserNotExistsException {
        User user = userExtractor.findById(userId).orElse(null);

        if (user == null) {
            throw new UserNotExistsException();
        }

        userRemover.delete(user);
    }

}
