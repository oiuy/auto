package ru.eldorado.web.elements.checkout.cart;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

public class CartItem extends AbstractElement {
    @FindBy(how = How.CSS, css = ".countProd")
    private SelenideElement count;

    public String getCode() {
        return getSelf().getAttribute("id").replaceAll("bskIds_", "");
    }

    public int getCount() {
        return Integer.valueOf(count.val());
    }

    public void setCount(String value) {count.setValue(value); }
}
