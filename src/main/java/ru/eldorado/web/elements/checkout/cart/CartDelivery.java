package ru.eldorado.web.elements.checkout.cart;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

public class CartDelivery extends AbstractElement {
    private static final String PICKUP = "Самовывоз";
    private static final String DELIVERY = "Доставка.*";

    @FindBy(how = How.ID, id = "delivery_radio_pickup-styler")
    private SelenideElement pickUp;

    public boolean isPickUp() {
        return getSelf().findElement(By.className("checkedBlock")).
                findElement(By.className("delivery-label")).getText().equals(PICKUP);
    }

    public boolean isDelivery() {
        return getSelf().findElement(By.className("checkedBlock")).
                findElement(By.className("delivery-label")).getText().equals(DELIVERY);
    }

    public void choosePickUp() {
        pickUp.click();
    }
}
