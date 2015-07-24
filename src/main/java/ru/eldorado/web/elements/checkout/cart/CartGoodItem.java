package ru.eldorado.web.elements.checkout.cart;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

import java.util.List;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class CartGoodItem extends AbstractElement {
    @FindBy(how = How.CSS, css = ".countProd")
    private SelenideElement countField;

    //Для Хайбрис:
    /*public String getCode() {
        return getSelf().getAttribute("id").replaceAll("bskIds_", "");
    }*/

    //Для Битрикс:
    public String getCode() {
       return getSelf().getAttribute("product-xml-id").trim();
    }

    public int getCount() {
        return Integer.valueOf(countField.val());
    }

    public void setCount(String value) {
        countField.setValue(value); }

    public void selectService (String type) {
        WebDriver driver = getWebDriver();
        List<WebElement> checks = driver.findElements(By.className("checkbox"));
        for(WebElement check : checks){
            if (check.getAttribute("rel").contains(type) & !check.getAttribute("rel").contains("0"))
            {check.click();
             return;}
        }
        throw new AssertionError("There's no " + type + " for item №:" + this.getCode());
    }

}
