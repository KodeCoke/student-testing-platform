package ru.teadev.testingplatform.authorization.vaadin.user;

import java.util.HashSet;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ru.teadev.testingplatform.authorization.domain.user.exception.BlankLoginException;
import ru.teadev.testingplatform.authorization.domain.user.exception.BlankPasswordException;
import ru.teadev.testingplatform.authorization.domain.user.exception.LoginAlreadyExistsException;
import ru.teadev.testingplatform.authorization.usecase.user.RegisterUser;

@Route("/user/registration")
public class UserRegistrationForm extends VerticalLayout {

    public UserRegistrationForm(@Autowired RegisterUser registerUser) {

        VerticalLayout mainLayout = new VerticalLayout();

        H1 header = new H1("Регистрация нового пользователя");

        TextField loginTextField = new TextField();
        loginTextField.setRequired(true);
        loginTextField.setLabel("Введите логин:");

        PasswordField passwordField = new PasswordField();
        passwordField.setRequired(true);
        passwordField.setRevealButtonVisible(true);
        passwordField.setLabel("Введите пароль:");

        Button registerButton = new Button();
        registerButton.setText("Зарегистрировать");
        registerButton.addClickListener(click -> {
            try {

                registerUser.execute(loginTextField.getValue(), passwordField.getValue(), new HashSet<>());
                loginTextField.clear();
                passwordField.clear();
                Notification.show("Пользователь зарегистрирован")
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            } catch (BlankLoginException | BlankPasswordException | LoginAlreadyExistsException e) {
                Notification.show(e.getClass().getSimpleName())
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });

        mainLayout.add(
                header,
                loginTextField,
                passwordField,
                registerButton
        );

        add(mainLayout);
        mainLayout.setMaxWidth(700, Unit.PIXELS);
        setAlignItems(Alignment.CENTER);
    }

}
