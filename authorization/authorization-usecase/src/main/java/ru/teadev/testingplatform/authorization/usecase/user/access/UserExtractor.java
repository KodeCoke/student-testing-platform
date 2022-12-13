package ru.teadev.testingplatform.authorization.usecase.user.access;

import java.util.Optional;

import lombok.NonNull;
import ru.teadev.testingplatform.authorization.domain.user.User;
import ru.teadev.testingplatform.authorization.domain.user.UserId;

public interface UserExtractor {

    Optional<User> findById(@NonNull UserId id);

    Optional<User> findByLogin(@NonNull String login);

}
