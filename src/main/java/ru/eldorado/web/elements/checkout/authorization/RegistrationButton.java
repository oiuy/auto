package ru.eldorado.web.elements.checkout.authorization;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

/**
 * Created by OsipovI on 23.07.2015.
 */
public class RegistrationButton extends AbstractElement {
    @FindBy (how = How.CSS, css = ".button")
    private SelenideElement button;

    public void click () {
        button.click();
    }
}
