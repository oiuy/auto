package ru.eldorado.web.pages.checkout;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.checkout.authorization.LeftBlock;
import ru.eldorado.web.elements.checkout.authorization.RegistrationButton;
import ru.eldorado.web.elements.checkout.authorization.RightBlock;
import ru.eldorado.web.pages.AbstractPage;

public class AuthorizationPage extends AbstractPage {
    @FindBy(how = How.CSS, css = ".checoutBlockLeft")
    private LeftBlock leftBlock;

    @FindBy(how = How.CSS, css = ".checoutBlockRight")
    private RightBlock rightBlock;

    public void noRegistration () {
        rightBlock.buyWithoutRegistration();
    }

    public void logIn (String login, String password) {
        leftBlock.logIn(login, password);
    }

    public void Registration (String email, String name, String surname, String password) {
        rightBlock.registration(email, name, surname, password);
    }
}