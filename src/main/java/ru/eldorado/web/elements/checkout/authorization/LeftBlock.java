package ru.eldorado.web.elements.checkout.authorization;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

/**
 * Created by OsipovI on 27.08.2015.
 */
public class LeftBlock extends AbstractElement {
    @FindBy(how = How.NAME, name = "USER_LOGIN")
    private SelenideElement loginField;

    @FindBy(how = How.NAME, name = "USER_PASSWORD")
    private SelenideElement passwordField;

    @FindBy(how = How.CSS, css = ".addToCartBigCP")
    private SelenideElement loginButton;

    public void logIn(String userName, String password) {
        loginField.setValue(userName);
        passwordField.setValue(password);
        loginButton.click();
    }
}
