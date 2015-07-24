package ru.eldorado.web.pages.checkout;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.checkout.cart.CartDelivery;
import ru.eldorado.web.elements.checkout.cart.CartGoodList;
import ru.eldorado.web.elements.checkout.cart.CartTotalPart;
import ru.eldorado.web.pages.WithNavigationPage;
import ru.eldorado.web.popups.cart.CartPopup;

public class CartPage extends WithNavigationPage {
    @FindBy(how = How.ID, id = "delivery-box")
    public CartDelivery deliveryBox;

    @FindBy(how = How.CSS, css = ".basketBlock")
    public CartGoodList cartGoodList;

    @FindBy(how = How.CSS, css = ".cartTotalPart")
    public CartTotalPart cartTotalPart;

    @FindBy(how = How.ID, id = "cartItemPopup")
    public CartPopup cartPopup;

}
