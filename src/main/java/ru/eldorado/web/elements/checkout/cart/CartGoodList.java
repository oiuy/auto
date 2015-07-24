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

    //Hybris functions, n/a for Birix
    public boolean isProductPresent(String productCode) {
        for (SelenideElement item : items) {
            CartGoodItem cartGoodItem = initContainer(item, CartGoodItem.class);
            if (cartGoodItem.getCode().equals(productCode)) {
                return true;
            }
        }
        return false;
    }

    public int numberOfProduct(String productCode) {
        for (SelenideElement item : items) {
            CartGoodItem cartGoodItem = initContainer(item, CartGoodItem.class);
            if (cartGoodItem.getCode().equals(productCode)) {
                return cartGoodItem.getCount();
            }
        }
        return 0;
    }

    //Bitrix functions:
    public void setProductCount (String product, String count) {
        for (int i=1; i <= items.size(); i=i+2)
       {
           CartGoodItem cartGoodItem = initContainer(items.get(i), CartGoodItem.class);
           if (cartGoodItem.getCode().equals(product)){
               cartGoodItem.setCount(count);
               return;
           }
       }
        throw new AssertionError("Item ¹:" + product + " not found!");
    }

    public void selectAdditionalService (String product, String type) {
        for (int i=1; i<= items.size(); i=i+2)
        {
            CartGoodItem cartGoodItem = initContainer(items.get(i), CartGoodItem.class);
            if (cartGoodItem.getCode().equals(product)){
                cartGoodItem.selectService(type);
                return;
            }
        }
    }

}
