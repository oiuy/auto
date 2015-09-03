package ru.eldorado.web.pages.checkout;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.checkout.delivery.OptionsContainer;
import ru.eldorado.web.elements.checkout.delivery.PersonalInfoBlock;
import ru.eldorado.web.elements.checkout.delivery.SubmitButton;
import ru.eldorado.web.pages.AbstractPage;

import java.util.List;

/**
 * Created by OsipovI on 23.07.2015.
 */
public class DeliveryPage extends AbstractPage {
    //personal info block
    @FindBy(how = How.CSS, css = ".checkOut__container--left-block")
    public PersonalInfoBlock personalBlock;

    //date & time block
    @FindBy(how = How.ID, id = "q-delivery-time")
    private SelenideElement timeContainer;

    @FindBy(how = How.CSS, css = ".q-delivery-time-item")
    private List<SelenideElement> timeList;

    //address block
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

    @FindBy(how = How.CSS, css = ".checkOut__container--right")
    private SubmitButton submitButton;


    public void submit() {
        submitButton.click();
    }

    public void setTime (String time) {
        timeContainer.click();
        for(SelenideElement item : timeList) {
            if (item.text().contains(time)) {
                item.click();
            }
        }
    }

}
