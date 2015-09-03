package ru.eldorado.web.pages.checkout;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.pages.AbstractPage;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ThankYouPage extends AbstractPage {

    //Õאיבנטס:
    @FindBy(how = How.CSS, css = ".orderTitle")
    private SelenideElement orderTitle;

    public String getOrderNumber() {
        return orderTitle.getText().replaceAll("[^0-9]", "");
    }

    public String getOrderNumberBitrix () {
        WebDriver driver = getWebDriver();
        String [] subUrl = driver.getCurrentUrl().split("ORDER_ID=");
        return subUrl[1].substring(0, 10);
    }


}
