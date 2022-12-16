package ru.teadev.testingplatform.authorization.domain.user;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class UserId {

    @NonNull
    UUID id;
}
