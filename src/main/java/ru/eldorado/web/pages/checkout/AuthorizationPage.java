package ru.eldorado.web.pages.checkout;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.checkout.authorization.RegistrationButton;
import ru.eldorado.web.pages.AbstractPage;

public class AuthorizationPage extends AbstractPage {
    @FindBy(how = How.ID, id = "order_no_registration")
    private SelenideElement orderNoRegistration;

    @FindBy(how = How.CSS, css = ".checoutBlockRight")
    private SelenideElement rightBlock;

    @FindBy(how = How.NAME, name = "USER_LOGIN")
    private SelenideElement loginField;

    @FindBy(how = How.NAME, name = "USER_PASSWORD")
    private SelenideElement passwordField;

    @FindBy(how = How.CSS, css = ".addToCartBigCP")
    private SelenideElement loginButton;

    @FindBy(how = How.NAME, name = "NEW_EMAIL")
    private SelenideElement emailField;

    @FindBy(how = How.NAME, name = "NEW_NAME")
    private SelenideElement firstName;

    @FindBy(how = How.NAME, name = "NEW_LAST_NAME")
    private SelenideElement lastName;

    @FindBy(how = How.ID, id = "USER_PASSWORD_REG_PASSWORD")
    private SelenideElement newPassword;

    @FindBy(how = How.CSS, css = ".checoutBlockRightFoot_buttons")
    private RegistrationButton regButton;

    public void buyWithoutRegistration() {
        rightBlock.setSelected(true);
        orderNoRegistration.click();
    }

    public void logIn(String userName, String password) {
        loginField.setValue(userName);
        passwordField.setValue(password);
        loginButton.click();
    }

    public void registration (String email, String name, String surname, String password) {
        rightBlock.setSelected(true);
        emailField.setValue(email);
        firstName.setValue(name);
        lastName.setValue(surname);
        newPassword.setValue(password);
        regButton.click();
    }

}