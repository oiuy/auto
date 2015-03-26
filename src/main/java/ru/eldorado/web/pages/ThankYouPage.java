package ru.eldorado.web.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.core.Order;

public class ThankYouPage extends AbstractPage {
    @FindBy(how = How.CSS, css = ".orderTitle")
    private SelenideElement orderTitle;

    private static final String ORDER_STATUS = "RECEIVED";

    public void finishOrderCreation(){
        Order order = Order.getInstance();
        order.setAttribute(Order.ID, orderTitle.getText().replaceAll("[^0-9]", ""));
        order.setAttribute(Order.STATUS, ORDER_STATUS);
    }
}
