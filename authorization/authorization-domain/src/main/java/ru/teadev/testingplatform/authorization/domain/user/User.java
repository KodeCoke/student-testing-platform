package ru.teadev.testingplatform.authorization.domain.user;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.util.Collections.unmodifiableSet;
import static org.apache.commons.lang3.StringUtils.isBlank;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.teadev.testingplatform.authorization.domain.user.exception.BlankLoginException;
import ru.teadev.testingplatform.authorization.domain.user.exception.BlankPasswordException;
import ru.teadev.testingplatform.authorization.domain.user.exception.LoginAlreadyExistsException;

@Data
@Setter(AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    public static User create(@NonNull String login,
                              @NonNull String password,
                              @NonNull LoginAlreadyExists loginAlreadyExists)
            throws BlankLoginException, BlankPasswordException, LoginAlreadyExistsException {

        if (isBlank(login)) {
            throw new BlankLoginException();
        }
        if (isBlank(password)) {
            throw new BlankPasswordException();
        }
        if (loginAlreadyExists.execute(login)) {
            throw new LoginAlreadyExistsException();
        }


        return new User(new UserId(UUID.randomUUID()), login, password);
    }

    @NonNull
    @EqualsAndHashCode.Include
    private UserId id;
    @NonNull
    private String login;
    @NonNull
    private String password;
    @NonNull
    private Set<Role> roles = new HashSet<>();

    public void addRole(@NonNull Role role) {
        roles.add(role);
    }

    public void addRoles(@NonNull Collection<@NonNull Role> roles) {
        this.roles.addAll(roles);
    }

    public void removeRole(@NonNull Role role) {
        roles.remove(role);
    }

    public Set<Role> getRoles() {
        return unmodifiableSet(roles);
    }

    public void clearRoles() {
        roles.clear();
    }

    public boolean hasRole(@NonNull Role role) {
        return roles.contains(role);
    }
}
