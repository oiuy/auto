package ru.eldorado.web.elements.checkout.pickup;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

public class ShopView extends AbstractElement {
    @FindBy(how = How.CSS, css = ".addressConfirmSubmitButton")
    private SelenideElement collectFromHereButton;

    @FindBy(how = How.CSS, css = ".pickupListTKName")
    private SelenideElement name;

    public String getName() {
        return name.text().trim();
    }

    public void collectFromHereClick() {
        collectFromHereButton.click();
    }
}
