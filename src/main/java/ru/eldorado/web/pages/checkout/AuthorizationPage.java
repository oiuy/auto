package ru.eldorado.web.pages.checkout;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.pages.AbstractPage;

public class AuthorizationPage extends AbstractPage {
    @FindBy(how = How.ID, id = "order_no_registration")
    private SelenideElement orderNoRegistration;

    public void buyWithoutRegistration() {
        orderNoRegistration.click();
    }
}