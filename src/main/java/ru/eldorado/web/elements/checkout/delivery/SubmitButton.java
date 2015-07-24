package ru.eldorado.web.elements.checkout.delivery;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

/**
 * Created by OsipovI on 23.07.2015.
 */
public class SubmitButton extends AbstractElement {
    @FindBy(how = How.TAG_NAME, tagName = "input")
    private SelenideElement button;

    public void click () {button.click();}
}
