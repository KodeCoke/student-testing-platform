package ru.teadev.testingplatform.authorization.domain.session;

import java.util.UUID;

import lombok.NonNull;
import lombok.Value;

@Value
public class SessionId {
    @NonNull
    UUID id;
}
