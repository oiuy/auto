package ru.eldorado.web.pages.checkout;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.base.SelectElement;
import ru.eldorado.web.elements.checkout.pickup.ScrollBar;
import ru.eldorado.web.elements.checkout.pickup.ShopList;
import ru.eldorado.web.elements.checkout.pickup.ShopView;
import ru.eldorado.web.pages.AbstractPage;

public class PickupPage extends AbstractPage {
    @FindBy(how = How.CSS, css = ".addressConfirmSubmitButton")
    private SelenideElement collectFromHereButton;

    //Выбор города для Хайбрис
    @FindBy(how = How.ID, id = "cuselFrame-one_more_select")
    public SelectElement citySelect;

    //Выбор города для Битрикс
    @FindBy(how = How.ID, id = "cusel-scroll-one_more_select")
    public SelectElement citySelectBitrix;

    @FindBy(how = How.ID, id = "listWrapper")
    public ShopList shopList;

    //Выбранный магазин для Хайбрис:
    @FindBy(how = How.CSS, css = "#rightList > .item[style='']")
    public ShopView shopView;

    //Выбранный магазин для Битрикс:
    @FindBy(how = How.CSS, css = ".pickupListRightSide")
    public ShopView shopViewBitrix;

    @FindBy(how = How.CSS, css = ".scrollbar")
    private ScrollBar scrollBar;

    public void findShop(String address) {
        if (shopList.isAddressPresents(address))
        { return; }
        else {
            scrollBar.scrollDown();
            findShop(address);
        }

    }

 }
