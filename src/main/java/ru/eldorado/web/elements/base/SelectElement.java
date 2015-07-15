package ru.eldorado.web.elements.base;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

public class SelectElement extends AbstractElement {
    @FindBy(how = How.CSS, css = "input[type='hidden']")
    private SelenideElement hidden;

    public String getValue() {
        return hidden.getAttribute("value");
    }

    public void setValue (String value) { hidden.selectOption(value); }
}
