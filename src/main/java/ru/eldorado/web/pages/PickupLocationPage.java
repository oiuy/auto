package ru.eldorado.web.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.eldorado.web.core.Order;
import static com.codeborne.selenide.WebDriverRunner.url;

import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class PickupLocationPage extends AbstractPage {
    @FindBy(how= How.CSS, css = ".blockpickupList")
    private SelenideElement shopList;

    @FindBy(how= How.CSS, css = ".addressConfirmSubmitButton")
    private SelenideElement collectFromHereButton;

    @FindBy(how= How.CSS, css = ".sd-avilable-cities")
    private SelenideElement cuselForAvlbCities;

    //@FindBy(how= How.CSS, css = ".sd-avilable-cities")

    private static final String CURRENT_CITY_TAG = "cuselText";

    private static final String CITY_CODE  = "62000001000";

    private static final String CITY = "Рязань";

    private static final String UNAVAILABLE = "нет в наличии";

    public String chooseAnyShop(){
        if(!currentCity().equals(CITY)) {
            chooseCity();
        }
            (new WebDriverWait(getWebDriver(), 20))
                    .until(ExpectedConditions.visibilityOf(shopList));
        List<WebElement> shops = shopList.findElements(By.className("pickupListAdresBlock"));
        for(WebElement shop: shops){
            if(!shop.findElement(By.className("pickupListTimes")).getText().equals(UNAVAILABLE)){
                shop.findElement(By.className("pickupListAdres")).click();
                (new WebDriverWait(getWebDriver(), 20))
                        .until(ExpectedConditions.visibilityOf(collectFromHereButton));
                Order.getInstance().setAttribute(Order.POINTOFSERVICE,
                        shop.findElement(By.className("pickupListAdres")).getText().trim());
                collectFromHereButton.click();
                return url();
            }
        }
        System.out.println("There is no shop to pickup order");
        return url();
    }

    public void chooseCity(){
        cuselForAvlbCities.findElement(By.id("cuselFrame-one_more_select")).click();
        (new WebDriverWait(getWebDriver(), 20))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("cusel-scroll-wrap")));
        List<WebElement> cities = cuselForAvlbCities.findElement(By.id("cusel-scroll-one_more_select")).
                findElements(By.cssSelector("*"));
        for(WebElement city: cities){
            if(city.getAttribute("val").equals(CITY)) city.click();
        }
    }

    private String currentCity(){
        return cuselForAvlbCities.findElement(By.className(CURRENT_CITY_TAG)).getText().trim();
    }
}
