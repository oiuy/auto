package ru.eldorado.web.pages.checkout;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import ru.eldorado.web.elements.base.SelectElement;
import ru.eldorado.web.elements.checkout.cart.CartDelivery;
import ru.eldorado.web.elements.checkout.cart.CartGoodList;
import ru.eldorado.web.elements.checkout.cart.CartItem;
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

    @FindBy(how = How.ID, id = "cuselFrame-self_delivery_city_select")
    public SelenideElement selectFrame;

    @FindBy(how = How.XPATH, xpath = "//div[@id='cusel-scroll-self_delivery_city_select']/span[2]")
    public SelenideElement citySelect;

   public void selectCity () {
       selectFrame.click();
       citySelect.click();
    }

}
