package ru.teadev.testingplatform.authorization.domain.user;

public interface LoginAlreadyExists {

    boolean execute(String login);

}
