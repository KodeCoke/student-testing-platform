package ru.teadev.testingplatform.authorization.vaadin.user.userlist;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ru.teadev.testingplatform.authorization.domain.user.User;
import ru.teadev.testingplatform.authorization.domain.user.exception.UserNotExistsException;
import ru.teadev.testingplatform.authorization.usecase.user.DeleteUser;
import ru.teadev.testingplatform.authorization.usecase.user.FindAllUsers;

import java.util.Optional;

@Route("/user/all")
@PageTitle("Все пользователи")
public class ListAllUsersForm extends VerticalLayout {

    private final VerticalLayout mainLayout = new VerticalLayout();
    private final HorizontalLayout buttonsLayout = new HorizontalLayout();
    private final Button editButton = new Button();
    private final Button deleteButton = new Button();
    private final Grid<User> usersGrid = new Grid<>();

    public ListAllUsersForm(@Autowired FindAllUsers findAllUsers,
                            @Autowired DeleteUser deleteUser) {

        usersGrid.setSelectionMode(Grid.SelectionMode.SINGLE);

        editButton.setIcon(new Icon(VaadinIcon.EDIT));
        editButton.setText("Редактировать");

        deleteButton.setIcon(new Icon(VaadinIcon.CLOSE));
        deleteButton.setText("Удалить");
        deleteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        deleteButton.addClickListener(deleteEvent -> {
            Optional<User> selectedUser = usersGrid.getSelectionModel().getFirstSelectedItem();
            if (selectedUser.isPresent()) {
                deleteSelectedUser(deleteUser, selectedUser.get());
                usersGrid.setItems(findAllUsers.execute());
            }
        });

        buttonsLayout.add(
                editButton,
                deleteButton
        );

        usersGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        usersGrid.addColumn(User::getLogin).setHeader("Логин");
        usersGrid.setItems(findAllUsers.execute());

        mainLayout.add(
                buttonsLayout,
                usersGrid
        );

        add(mainLayout);
    }

    private void deleteSelectedUser(DeleteUser deleteUser,
                                    User selectedUser) {
        if (selectedUser != null) {
            try {
                String login = selectedUser.getLogin();
                deleteUser.execute(selectedUser.getId());
                Notification.show("Пользователь " + login + " удалён")
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            } catch (UserNotExistsException e) {
                Notification.show(e.getClass().getSimpleName())
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        }
    }

}
