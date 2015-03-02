package ru.eldorado.web.elements.header;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

import static com.codeborne.selenide.Selenide.$;

public class HeaderMenu extends AbstractElement {
    @FindBy(how = How.CSS, css = "li.headerMenuItem a[title='Адреса магазинов']")
    private SelenideElement addresses;

    @FindBy(how = How.CSS, css = "li.headerMenuItem a[title='Доставка']")
    private SelenideElement delivery;

    public void goToShopAddresses() {
        $(addresses).click();
    }

    public void goToDelivery() {
        $(delivery).click();
    }
}
