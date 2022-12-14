package ru.teadev.testingplatform.authorization.domain.user;

import java.util.Set;

import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class UserRestorer {

    @NonNull
    public User restore(@NonNull UserId userId,
                        @NonNull String login,
                        @NonNull String password,
                        @NonNull Set<Role> roles) {
        return new User(userId, login, password, roles);
    }

}
