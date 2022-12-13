package ru.teadev.testingplatform.authorization.usecase.user.invariants;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.teadev.testingplatform.authorization.domain.user.LoginAlreadyExists;
import ru.teadev.testingplatform.authorization.usecase.user.access.UserExtractor;

@Service
@RequiredArgsConstructor
public class LoginAlreadyExistsUsesUserExtractor implements LoginAlreadyExists {

    private final UserExtractor userExtractor;

    @Override
    public boolean execute(String login) {
        return userExtractor.findByLogin(login)
                .isPresent();
    }
}
