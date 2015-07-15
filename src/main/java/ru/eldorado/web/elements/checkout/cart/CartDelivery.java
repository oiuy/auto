package ru.eldorado.web.elements.checkout.cart;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

public class CartDelivery extends AbstractElement {
    private static final String PICKUP = "Самовывоз";
    private static final String DELIVERY = "Доставка.*";

    //Самовывоз для Хайбрис
    @FindBy(how = How.ID, id = "delivery_radio_pickup-styler")
    private SelenideElement pickUp;

    //Самовывоз для Битркис
    @FindBy(how = How.ID, id = "delivery_radio_26-styler")
    private SelenideElement getPickUpBitrix;

    //Доставки для Битрикс
    @FindBy(how = How.ID, id = "delivery_radio_36-styler")
    private  SelenideElement deliveryMkadBitrix;

    @FindBy(how = How.ID, id = "delivery_radio_8-styler")
    private SelenideElement deliveryFarBitrix;


    public boolean isPickUp() {
        return getSelf().findElement(By.className("checkedBlock")).
                findElement(By.className("delivery-label")).getText().equals(PICKUP);
    }

    public boolean isDelivery() {
        return getSelf().findElement(By.className("checkedBlock")).
                findElement(By.className("delivery-label")).getText().equals(DELIVERY);
    }

    public void choosePickUp() {
        getPickUpBitrix.click();
    }

    public void chooseDelivery() {
        deliveryMkadBitrix.click();
    }
}
