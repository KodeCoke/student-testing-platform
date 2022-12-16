package ru.teadev.testingplatform.authorization.vaadin.user.registration;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ru.teadev.testingplatform.authorization.domain.user.exception.BlankLoginException;
import ru.teadev.testingplatform.authorization.domain.user.exception.BlankPasswordException;
import ru.teadev.testingplatform.authorization.domain.user.exception.LoginAlreadyExistsException;
import ru.teadev.testingplatform.authorization.usecase.user.RegisterUser;

import java.util.HashSet;

@Route("/user/registration")
@PageTitle("Регистрация нового пользователя")
public class UserRegistrationForm extends VerticalLayout {

    private final VerticalLayout mainLayout = new VerticalLayout();
    private final TextField loginTextField = new TextField();
    private final TextField passwordTextField = new TextField();
    private final Button registerButton = new Button();

    public UserRegistrationForm(@Autowired RegisterUser registerUser) {
        loginTextField.setLabel("Введите логин:");
        passwordTextField.setLabel("Введите пароль:");
        registerButton.setText("Зарегистрировать");
        registerButton.addClickListener(click -> {
            try {
                registerUser.execute(loginTextField.getValue(), passwordTextField.getValue(), new HashSet<>());
                loginTextField.clear();
                passwordTextField.clear();
                Notification.show("Пользователь зарегистрирован")
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            } catch (BlankLoginException | BlankPasswordException | LoginAlreadyExistsException e) {
                Notification.show(e.getClass().getSimpleName())
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });

        mainLayout.add(
                loginTextField,
                passwordTextField,
                registerButton
        );

        add(mainLayout);
    }

}
