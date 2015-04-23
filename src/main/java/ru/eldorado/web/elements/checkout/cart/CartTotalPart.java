package ru.eldorado.web.elements.checkout.cart;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

import static org.junit.Assert.assertTrue;

public class CartTotalPart extends AbstractElement {
    private static final String FREE = "бесплатно";

    @FindBy(how = How.CSS, css = ".totals-delivery-label")
    private SelenideElement delivery;

    @FindBy(how = How.ID, id = "deliv_price_id")
    private SelenideElement deliveryTotal;

    @FindBy(how = How.ID, id = "goods_val_td")
    private SelenideElement productTotal;

    @FindBy(how = How.ID, id = "total_price_id")
    private SelenideElement total;

    @FindBy(how = How.CSS, css = ".doCheckoutBut")
    private SelenideElement doCheckoutButton;

    public int deliveryCost() {
        try {
            return Integer.valueOf(deliveryTotal.getText().replaceAll("[^0-9]", ""));
        } catch (NumberFormatException ignored) {
        }

        return 0;
    }

    public int productsPrice() {
        try {
            return Integer.parseInt(productTotal.getText().replaceAll("[^0-9]", ""));
        } catch (NumberFormatException ignored) {
        }

        return 0;
    }

    public int totalPrice() {
        try {
            return Integer.parseInt(total.getText().replaceAll("[^0-9]", ""));
        } catch (NumberFormatException ignored) {
        }

        return 0;
    }

    public void assertTotalPriceSum() {
        assertTrue(totalPrice() == deliveryCost() + productsPrice());
    }

    public void checkout() {
        doCheckoutButton.click();
    }
}
