package ru.teadev.testingplatform.authorization.usecase.user;

import ru.teadev.testingplatform.authorization.domain.user.User;

import java.util.List;

public interface FindAllUsers {

    List<User> execute();

}
