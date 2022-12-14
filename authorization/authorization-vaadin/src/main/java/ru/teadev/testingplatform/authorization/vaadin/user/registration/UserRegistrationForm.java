package ru.teadev.testingplatform.authorization.vaadin.user.registration;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ru.teadev.testingplatform.authorization.domain.user.exception.BlankLoginException;
import ru.teadev.testingplatform.authorization.domain.user.exception.BlankPasswordException;
import ru.teadev.testingplatform.authorization.domain.user.exception.LoginAlreadyExistsException;
import ru.teadev.testingplatform.authorization.usecase.user.RegisterUser;

import java.util.HashSet;

@Route("/user/registration")
public class UserRegistrationForm extends VerticalLayout {

    public UserRegistrationForm(@Autowired RegisterUser registerUser) {

        VerticalLayout mainLayout = new VerticalLayout();

        H1 header = new H1("Регистрация нового пользователя");

        TextField loginTextField = new TextField();
        loginTextField.setLabel("Введите логин:");

        TextField passwordTextField = new TextField();
        passwordTextField.setLabel("Введите пароль:");

        Button registerButton = new Button();
        registerButton.setText("Зарегистрировать");
        registerButton.addClickListener(click -> {
            try {
                registerUser.execute(loginTextField.getValue(), passwordTextField.getValue(), new HashSet<>());
                loginTextField.clear();
                passwordTextField.clear();
                Notification.show("Пользователь зарегистрирован").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            } catch (BlankLoginException | BlankPasswordException | LoginAlreadyExistsException e) {
                Notification.show(e.getLocalizedMessage()).addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });

        mainLayout.add(
                header,
                loginTextField,
                passwordTextField,
                registerButton
        );
    }

}
