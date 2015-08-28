package ru.eldorado.web.pages.checkout;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.checkout.delivery.SubmitButton;
import ru.eldorado.web.pages.AbstractPage;

import java.util.List;

/**
 * Created by OsipovI on 23.07.2015.
 */
public class PaymentPage extends AbstractPage {

    @FindBy(how = How.CSS, css = ".checkOut__container--right")
    private SubmitButton submitButton;

    @FindBy(how = How.ID, id = "delivery_radio_1-styler")
    private SelenideElement radioCash;

    @FindBy(how = How.ID, id = "delivery_radio_2-styler")
    private SelenideElement radioCard;

    @FindBy(how = How.ID, id = "delivery_radio_168-styler")
    private SelenideElement radioOnline;

    public void selectCash () {radioCash.click();}

    public void selectCard () {radioCard.click();}

    public void selectOnline () {radioOnline.click();}

    public void submit () {submitButton.click();}
}
