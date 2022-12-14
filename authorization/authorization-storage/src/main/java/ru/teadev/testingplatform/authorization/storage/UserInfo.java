package ru.teadev.testingplatform.authorization.storage;

import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;
import ru.teadev.testingplatform.authorization.domain.user.Role;
import ru.teadev.testingplatform.authorization.domain.user.UserId;

@Value
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserInfo {

    @NonNull
    @EqualsAndHashCode.Include
    UserId id;
    @NonNull
    String login;
    @NonNull
    String password;
    @NonNull
    Set<Role> roles;
}
