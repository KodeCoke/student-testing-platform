package ru.teadev.testingplatform.authorization.domain.user;

import java.util.UUID;

import lombok.NonNull;
import lombok.Value;

@Value
public class UserId {
    @NonNull
    UUID id;
}
