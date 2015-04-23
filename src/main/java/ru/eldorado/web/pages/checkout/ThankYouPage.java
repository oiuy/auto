package ru.eldorado.web.pages.checkout;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.pages.AbstractPage;

public class ThankYouPage extends AbstractPage {
    @FindBy(how = How.CSS, css = ".orderTitle")
    private SelenideElement orderTitle;

    public String getOrderNumber() {
        return orderTitle.getText().replaceAll("[^0-9]", "");
    }
}
