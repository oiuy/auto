package ru.eldorado.web.pages.checkout;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
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

    public void setFocusOnRightBock() {
        rightBlock.setSelected(true);
    }

    public void buyWithoutRegistration() {
        orderNoRegistration.click();
    }

    public void login (String userName, String password) {
        loginField.setValue(userName);
        passwordField.setValue(password);
        loginButton.click();
    }

}