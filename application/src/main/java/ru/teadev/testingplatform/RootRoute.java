package ru.teadev.testingplatform;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import ru.teadev.testingplatform.authorization.vaadin.user.UserRegistrationForm;

@Route("")
@Tag("root")
public class RootRoute extends Component implements BeforeEnterObserver {

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        event.forwardTo(UserRegistrationForm.class);
    }
}
