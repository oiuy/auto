package ru.eldorado.web.popups.cart;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.popups.AbstractPopup;

public class CartPopup extends AbstractPopup {
    @FindBy(how = How.TAG_NAME, tagName = "p")
    private SelenideElement textTag;

    public String getText() {
        return textTag.getText();
    }
}
