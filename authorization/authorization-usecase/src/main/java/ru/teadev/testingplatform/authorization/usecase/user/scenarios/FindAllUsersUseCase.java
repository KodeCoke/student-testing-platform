package ru.teadev.testingplatform.authorization.usecase.user.scenarios;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.teadev.testingplatform.authorization.domain.user.User;
import ru.teadev.testingplatform.authorization.usecase.user.FindAllUsers;
import ru.teadev.testingplatform.authorization.usecase.user.access.UserExtractor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllUsersUseCase implements FindAllUsers {

    private final UserExtractor userExtractor;

    @Override
    public List<User> execute() {
        return userExtractor.findAll();
    }
}
