package ru.eldorado.web.pages.checkout;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.pages.AbstractPage;

public class ThankYouPage extends AbstractPage {

    //Хайбрис:
    @FindBy(how = How.CSS, css = ".orderTitle")
    private SelenideElement orderTitle;

    //Битрикс:
    @FindBy(how = How.CSS, css = ".orderFormed  ")
    private SelenideElement orderNumber;

    public String getOrderNumber() {
        return orderTitle.getText().replaceAll("[^0-9]", "");
    }

    public String getOrderNumberBitrix () {
        return  orderNumber.getText().replaceAll("[^0-9]", "").substring(0, 10);
    }
}
