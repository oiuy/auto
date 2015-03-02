package ru.eldorado.web.popups.header;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.popups.AbstractPopup;

import static com.codeborne.selenide.Selenide.$;

public class RegionConfirmPopup extends AbstractPopup {
    @FindBy(how = How.ID, id = "rightRegion")
    private SelenideElement yes;

    @FindBy(how = How.ID, id = "wrongRegion")
    private SelenideElement no;

    public void clickYes() {
        $(yes).click();
    }

    public void clickNo() {
        $(no).click();
    }
}
