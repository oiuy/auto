package ru.eldorado.web.elements.checkout.cart;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

import java.util.List;

/**
 * Created by OsipovI on 15.07.2015.
 */
public class CartCityList extends AbstractElement {
    @FindBy(how = How.TAG_NAME, tagName = "span")
    private List<SelenideElement> citiesList;

    public void findCity (String cityName) {
        for (int i=1; i <= citiesList.size(); i++) {
            if (citiesList.get(i).getText().trim().equals(cityName)) {
                citiesList.get(i).click();
                return;
            }
            else {throw new AssertionError("Can't find city: " + cityName);}
        }
    }
}
