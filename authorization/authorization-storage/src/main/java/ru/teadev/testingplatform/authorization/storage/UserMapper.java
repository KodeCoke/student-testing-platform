package ru.teadev.testingplatform.authorization.storage;

import org.mapstruct.Mapper;
import ru.teadev.testingplatform.authorization.domain.user.User;

@Mapper
public interface UserMapper {

    UserInfo toRecord(User user);
}
