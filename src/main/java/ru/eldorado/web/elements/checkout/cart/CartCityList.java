package ru.eldorado.web.elements.checkout.cart;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Created by OsipovI on 15.07.2015.
 */
public class CartCityList extends AbstractElement {
    @FindBy(how = How.TAG_NAME, tagName = "span")
    private List<SelenideElement> citiesList;

    @FindBy(how = How.CSS, css = ".jScrollPaneContainer")
    private SelenideElement scroller;

    public void scrollDown (int pix) {
        WebDriver driver = getWebDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollTop = arguments[1];", scroller, pix);
    }

    public void findCity(String cityName) {
        for (int i=1; i <= citiesList.size(); i = i++)
        {if (searcher(cityName, i)){
            return;
        } else {scrollDown(i*46);
                searcher(cityName, i++);
        }}
        throw new AssertionError("City: " + cityName + " not found!");
    }

    public boolean searcher (String title, int index) {
        for (int i=index; i<=index+5; i++)
        {if (citiesList.get(i).text().trim().equals(title)){
            citiesList.get(i).click();
            return true;
        }}
        return false;
    }
}
