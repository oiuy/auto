package ru.eldorado.web.elements.checkout.authorization;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

/**
 * Created by OsipovI on 27.08.2015.
 */
public class RightBlock extends AbstractElement {
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

    @FindBy(how = How.ID, id = "order_no_registration_site")
    private SelenideElement orderNoRegistration;

    public void buyWithoutRegistration() {
        orderNoRegistration.click();
    }

    public void registration (String email, String name, String surname, String password) {
        emailField.setValue(email);
        firstName.setValue(name);
        lastName.setValue(surname);
        newPassword.setValue(password);
        regButton.click();
    }
}
