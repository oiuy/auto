package ru.eldorado.web.pages.checkout;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.base.SelectElement;
import ru.eldorado.web.elements.checkout.pickup.ShopList;
import ru.eldorado.web.elements.checkout.pickup.ShopView;
import ru.eldorado.web.pages.AbstractPage;

public class PickupPage extends AbstractPage {
    @FindBy(how = How.CSS, css = ".addressConfirmSubmitButton")
    private SelenideElement collectFromHereButton;

    @FindBy(how = How.ID, id = "cuselFrame-one_more_select")
    public SelectElement citySelect;

    @FindBy(how = How.ID, id = "listWrapper")
    public ShopList shopList;

    @FindBy(how = How.CSS, css = "#rightList > .item[style='']")
    public ShopView shopView;
}
