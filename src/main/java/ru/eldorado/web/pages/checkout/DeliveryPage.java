package ru.eldorado.web.pages.checkout;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.checkout.delivery.OptionsContainer;
import ru.eldorado.web.elements.checkout.delivery.SubmitButton;
import ru.eldorado.web.pages.AbstractPage;

import java.util.List;

/**
 * Created by OsipovI on 23.07.2015.
 */
public class DeliveryPage extends AbstractPage {
    /*
    List of containers:
    0, 2 - date, time for Express delivery
    1, 3 - date, time for Standard delivery
    4 - lift type
    5 - phone type
    */
    @FindBy(how = How.CSS, css = ".roundedSelectSet")
    List<OptionsContainer> optionsContainers;

    @FindBy(how = How.ID, id = "metro_dropdown")
    public SelenideElement metro;

    @FindBy(how = How.ID, id = "ORDER_PROP_5_VALUE")
    public SelenideElement street;

    @FindBy(how = How.NAME, name = "ORDER_PROP_4")
    public SelenideElement house;

    @FindBy(how = How.NAME, name = "ORDER_PROP_39")
    public SelenideElement building;

    @FindBy(how = How.NAME, name = "ORDER_PROP_40")
    public SelenideElement corpus;

    @FindBy(how = How.NAME, name = "ORDER_PROP_42")
    public SelenideElement level;

    @FindBy(how = How.NAME, name = "ORDER_PROP_43")
    public SelenideElement flat;

    @FindBy(how = How.NAME, name = "ORDER_PROP_44")
    public SelenideElement code;

    @FindBy(how = How.ID, id = "deliveryContactName")
    public SelenideElement firstName;

    @FindBy(how = How.ID, id = "deliveryContactLastName")
    public SelenideElement lastName;

    @FindBy(how = How.NAME, name = "mobile_phone_code")
    public SelenideElement phoneCode;

    @FindBy(how = How.NAME, name = "mobile_phone_phone")
    public SelenideElement phoneNumber;

    @FindBy(how = How.NAME, name = "ORDER_PROP_6")
    public SelenideElement email;

    @FindBy(how = How.CSS, css = ".addToCartBigCP")
    private SubmitButton submitButton;

    public void submit() {
        submitButton.click();
    }

    public void selectDate (String date) {
        optionsContainers.get(1).selectOption(date);
    }

    public void selectTime (String time) {
        optionsContainers.get(3).selectOption(time);
    }

    public void selectLift (String type) {
        optionsContainers.get(4).selectOption(type);
    }

    public void selectPhoneType (String type) {
        optionsContainers.get(5).selectOption(type);
    }

}
