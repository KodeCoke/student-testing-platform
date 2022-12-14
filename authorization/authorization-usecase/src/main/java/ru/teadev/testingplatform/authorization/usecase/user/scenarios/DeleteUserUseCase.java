package ru.teadev.testingplatform.authorization.usecase.user.scenarios;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.teadev.testingplatform.authorization.domain.user.User;
import ru.teadev.testingplatform.authorization.domain.user.exception.UserNotFoundException;
import ru.teadev.testingplatform.authorization.usecase.user.DeleteUser;
import ru.teadev.testingplatform.authorization.usecase.user.access.UserExtractor;
import ru.teadev.testingplatform.authorization.usecase.user.access.UserRemover;

@Service
@RequiredArgsConstructor
public class DeleteUserUseCase implements DeleteUser {

    private final UserExtractor userExtractor;
    private final UserRemover userRemover;

    @Override
    public void execute(@NonNull User user) throws UserNotFoundException {

        if (userExtractor.findById(user.getId()).isEmpty()) {
            throw new UserNotFoundException();
        }

        userRemover.delete(user);
    }

}
