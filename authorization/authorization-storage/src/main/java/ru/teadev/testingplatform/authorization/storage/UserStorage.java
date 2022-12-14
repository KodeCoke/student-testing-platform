package ru.teadev.testingplatform.authorization.storage;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

import static java.util.Optional.ofNullable;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.teadev.testingplatform.authorization.domain.user.Role;
import ru.teadev.testingplatform.authorization.domain.user.User;
import ru.teadev.testingplatform.authorization.domain.user.UserId;
import ru.teadev.testingplatform.authorization.domain.user.UserRestorer;
import ru.teadev.testingplatform.authorization.usecase.user.access.UserExtractor;
import ru.teadev.testingplatform.authorization.usecase.user.access.UserPersister;
import ru.teadev.testingplatform.authorization.usecase.user.access.UserRemover;

@Service
@RequiredArgsConstructor
public class UserStorage implements UserExtractor, UserPersister, UserRemover {

    private final UserRestorer restorer;
    private final UserMapper mapper;

    private final ConcurrentHashMap<UserId, @NonNull UserInfo> storageMap = new ConcurrentHashMap<>();

    @PostConstruct
    void init() {
        UserId adminId = new UserId(UUID.randomUUID());

        storageMap.put(
                adminId,
                new UserInfo(
                        adminId,
                        "admin",
                        "admin",
                        Set.of(Role.values())));
    }

    @Override
    public Optional<User> findById(@NonNull UserId id) {
        return ofNullable(storageMap.get(id))
                .map(this::restore);
    }

    private User restore(UserInfo info) {
        return restorer.restore(
                info.getId(),
                info.getLogin(),
                info.getPassword(),
                new HashSet<>(info.getRoles()));
    }

    @Override
    public Optional<User> findByLogin(@NonNull String login) {
        return storageMap.values()
                .stream()
                .filter(user -> login.equals(user.getLogin()))
                .map(this::restore)
                .findFirst();
    }

    @Override
    @NonNull
    public User save(@NonNull User user) {
        storageMap.put(
                user.getId(),
                mapper.toRecord(user));

        return user;
    }

    @Override
    public void delete(@NonNull User user) {
        storageMap.remove(user.getId());
    }

    @Override
    public List<User> findAll() {
        return storageMap.values()
                .stream()
                .map(this::restore)
                .collect(Collectors.toList());
    }
}
