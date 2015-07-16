package ru.eldorado.web.elements.checkout.cart;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

import java.util.List;

public class CartDelivery extends AbstractElement {
    private static final String PICKUP = "���������";
    private static final String DELIVERY = "��������.*";

    //��������� ��� �������
    @FindBy(how = How.ID, id = "delivery_radio_pickup-styler")
    private SelenideElement pickUp;

    //��������� ��� �������
    @FindBy(how = How.ID, id = "delivery_radio_26-styler")
    private SelenideElement getPickUpBitrix;

    //�������� ��� �������
    @FindBy(how = How.ID, id = "delivery_radio_35-styler")
    private  SelenideElement deliveryMkadBitrix;

    @FindBy(how = How.ID, id = "cuselFrame-self_delivery_city_select")
    private SelenideElement selectFrame;

    @FindBy(how = How.ID, id ="cusel-scroll-self_delivery_city_select")
    private CartCityList cityList;

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

    public void selectCity(String title) {
        selectFrame.click();
        cityList.findCity(title);
    }
}
