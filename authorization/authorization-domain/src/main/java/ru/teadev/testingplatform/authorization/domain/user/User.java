package ru.teadev.testingplatform.authorization.domain.user;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.util.Collections.unmodifiableSet;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Setter(AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class User {

    public static User create(@NonNull String name) {
        return new User(new UserId(UUID.randomUUID()), name);
    }

    @NonNull
    private UserId id;
    @NonNull
    private String name;
    @NonNull
    private Set<Role> roles = new HashSet<>();

    public void addRole(@NonNull Role role) {
        roles.add(role);
    }

    public void removeRole(@NonNull Role role) {
        roles.remove(role);
    }

    public Set<Role> getRoles() {
        return unmodifiableSet(roles);
    }

    public boolean hasRole(@NonNull Role role) {
        return roles.contains(role);
    }
}
