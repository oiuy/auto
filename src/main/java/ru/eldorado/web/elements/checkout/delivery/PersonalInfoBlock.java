package ru.eldorado.web.elements.checkout.delivery;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

/**
 * Created by OsipovI on 28.08.2015.
 */
public class PersonalInfoBlock extends AbstractElement {
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
}
