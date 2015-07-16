package ru.eldorado.web.elements.checkout.cart;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

public class CartGoodItem extends AbstractElement {
    @FindBy(how = How.CSS, css = ".countProd")
    private SelenideElement count;

    @FindBy(how = How.CSS, css = ".basketBlockRow background_white")
    private SelenideElement element;

    //Для Хайбрис:
    /*public String getCode() {
        return getSelf().getAttribute("id").replaceAll("bskIds_", "");
    }*/

    //Для Битрикс:
    public String getCode() {
       return element.getAttribute("product-xml-id");
    }

    public int getCount() {
        return Integer.valueOf(count.val());
    }

    public void setCount(String value) {count.setValue(value); }

}
