package ru.eldorado.web.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.popups.RegionPopup;

public class MainPage extends WithNavigationPage {
    @FindBy(how = How.CLASS_NAME, className = "popupRegion")
    private RegionPopup regionPopup;

    public void selectRegion (String title) {
        if (regionPopup.isDisplayed()){
            regionPopup.selectCity(title);
        }
    }

}
