package ru.teadev.testingplatform.authorization.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Optional.ofNullable;

import lombok.NonNull;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.teadev.testingplatform.authorization.domain.user.User;
import ru.teadev.testingplatform.authorization.domain.user.UserId;
import ru.teadev.testingplatform.authorization.usecase.user.access.UserExtractor;
import ru.teadev.testingplatform.authorization.usecase.user.access.UserPersister;
import ru.teadev.testingplatform.authorization.usecase.user.access.UserRemover;

@Service
public class UserCollectionStorage implements UserExtractor, UserPersister, UserRemover {

    private final ConcurrentHashMap<UserId, @NonNull User> storageMap = new ConcurrentHashMap<>();

    @Override
    public Optional<User> findById(@NonNull UserId id) {
        return ofNullable(storageMap.get(id));
    }

    @Override
    public Optional<User> findByLogin(@NonNull String login) {
        return storageMap.values()
                .stream()
                .filter(user -> login.equals(user.getLogin()))
                .findFirst();
    }

    @Override
    public User save(User user) {
        storageMap.put(user.getId(), user);

        return user;
    }

    @Override
    public void delete(User user) {
        storageMap.remove(user.getId());
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(storageMap.values());
    }
}
