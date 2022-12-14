package ru.teadev.testingplatform.authorization.usecase.user.access;

import lombok.NonNull;
import ru.teadev.testingplatform.authorization.domain.user.User;

public interface UserRemover {

    void delete(@NonNull User user);
}
