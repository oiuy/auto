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
    @FindBy(how = How.CSS, css = ".paymentSelectLabelHd")
    private List<SelenideElement> payments;

    @FindBy(how = How.CSS, css = ".addToCartBigCP")
    private SubmitButton submitButton;

    public void selectCash () {payments.get(0).click();}

    public void selectCard () {payments.get(1).click();}

    public void secletOnline () {payments.get(2).click();}

    public void submit () {submitButton.click();}
}
