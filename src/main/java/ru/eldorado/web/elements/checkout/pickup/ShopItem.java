package ru.eldorado.web.elements.checkout.pickup;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

public class ShopItem extends AbstractElement {
    @FindBy(how = How.CSS, css = ".pickupListAdres")
    private SelenideElement title;

    public String getTitle() {
        return title.text().trim();
    }

    public boolean isThis(String title) {
        return getTitle().contains(title);
    }

    public void click() {
        getSelf().click();
    }

}
