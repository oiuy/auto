package ru.eldorado.web.popups;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

import java.util.List;

/**
 * Created by OsipovI on 23.07.2015.
 */
public class RegionPopup extends AbstractPopup {
    @FindBy(how = How.CLASS_NAME, className = "regionCity")
    private List<SelenideElement> cities;

    public void selectCity (String title) {
        for(SelenideElement city : cities) {
            if(city.getText().trim().equals(title)) {
                city.click();
                return;}
        }
        throw new AssertionError("City:" + title + " not found!");
    }

}
