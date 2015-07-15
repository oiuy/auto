package ru.eldorado.web.elements.catalog;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;
import ru.eldorado.web.utils.HybrisProps;

public class CatalogGoodItem extends AbstractElement {
    //товар уже в корзине для Хайбрис:
    // private static final String IN_BASKET = "inBasket";

    //товар уже в корзине для Битрикс:
    private static final String IN_BASKET = "alreadyInCart";

    @FindBy(how = How.CSS, css = ".addToCart")
    private SelenideElement addToCart;

    //Цена товара для Хайбриса:
    @FindBy(how = How.CSS, css = ".itemPriceNew")
    private SelenideElement price;

    //Цена товара для Битрикса:
    @FindBy(how = How.CSS, css = ".discountPrice")
    private SelenideElement priceBitrix;

    @FindBy(how = How.CSS, css = ".itemTitle")
    private SelenideElement title;

    public void addToCart() {
        addToCart.click();
        addToCart.waitUntil(Condition.cssClass(IN_BASKET), HybrisProps.WAIT_UNTIL_IN_MILLISECONDS);
    }

    public int getPrice() {
        try {
            return Integer.valueOf(priceBitrix.getText().replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            throw new AssertionError("Can't find price for product");
        }
    }

    public String getTitle() {
        return title.getText();
    }

    public String productCode() {
        return addToCart.getAttribute("data-product");
    }
}
