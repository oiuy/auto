package ru.eldorado.web.elements.checkout.pickup;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

import java.util.List;

public class ShopList extends AbstractElement {
    @FindBy(how = How.CSS, css = ".pickupListAdresItem")
    private List<ShopItem> shops;

    public int size() {
        return shops.size();
    }

    public void selectAddress(String address) {
        for (ShopItem shop : shops) {
            if (shop.isThis(address)) {
                shop.click();
                return;
            }
        }
        throw new AssertionError("Can't find address: " + address);
    }
}
