package ru.eldorado.web.elements.checkout.cart;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

import java.util.List;

public class CartGoodList extends AbstractElement {
    @FindBy(how = How.CSS, css = ".basketBlockRow")
    private List<SelenideElement> items;

    public boolean isCartEmpty() {
        return items.isEmpty();
    }

    public boolean isProductPresent(String productCode) {
        for (SelenideElement item : items) {
            CartItem cartItem = initContainer(item, CartItem.class);
            if (cartItem.getCode().equals(productCode)) {
                return true;
            }
        }

        return false;
    }

    public int numberOfProduct(String productCode) {
        for (SelenideElement item : items) {
            CartItem cartItem = initContainer(item, CartItem.class);
            if (cartItem.getCode().equals(productCode)) {
                return cartItem.getCount();
            }
        }

        return 0;
    }
}
