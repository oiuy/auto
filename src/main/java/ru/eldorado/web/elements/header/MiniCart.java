package ru.eldorado.web.elements.header;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

public class MiniCart extends AbstractElement {
    @FindBy(how = How.ID, id = "basketEmpty")
    public SelenideElement basketEmpty;

    @FindBy(how = How.ID, id = "basketNotEmpty")
    public SelenideElement basketNotEmpty;

    @FindBy(how = How.ID, id = "basketCount")
    public SelenideElement basketCount;

    @FindBy(how = How.ID, id = "basketCost")
    public SelenideElement basketCost;

    //—сылка на корзину в ’айбрис:
    @FindBy(how = How.ID, id = "cartLink")
    public SelenideElement cartLink;

    //—сылка на корзину в Ѕитрикс:
    @FindBy(how = How.CSS, css = ".headerCartLink")
    public SelenideElement cartLinkBitrix;

    public boolean isBasketEmpty() {
        return basketEmpty.isDisplayed();
    }

    public int basketCount() {
        try {
            return Integer.valueOf(basketCount.text());
        } catch (NumberFormatException ignored) {
        }

        return 0;
    }

    public int basketCost() {
        try {
            return Integer.valueOf(basketCost.getText().replaceAll("[^0-9]", ""));
        } catch (NumberFormatException ignored) {
        }

        return 0;
    }

    public void goToCart() {
        cartLinkBitrix.click();
    }

}
