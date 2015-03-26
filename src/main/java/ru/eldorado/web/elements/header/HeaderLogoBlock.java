package ru.eldorado.web.elements.header;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

public class HeaderLogoBlock extends AbstractElement {
    @FindBy(how = How.CSS, css = ".miniCart")
    public SelenideElement minicart;

    public WebElement getBasketEmpty(){
        return minicart.findElement(By.id("basketEmpty"));
    }

    public WebElement getBasketNotEmpty(){
        return minicart.findElement(By.id("basketNotEmpty"));
    }

    public boolean isBasketEmpty(){
        return getBasketEmpty().isDisplayed();
    }

    public boolean isBasketNotEmpty(){
        return getBasketNotEmpty().isDisplayed();
    }

    public int basketCount(){
        String count = minicart.findElement(By.id("basketCount")).getText();
        if(count == null || count.isEmpty() || count.equals(" ")) return 0;
        else return Integer.valueOf(count);
    }

    public int basketCost(){
        if (isBasketEmpty()) return 0;
        else {
            String costString = getBasketNotEmpty().findElement(By.id("basketCost")).getText();
            costString = costString.replaceAll("[^0-9]", "");
            return Integer.valueOf(costString);
        }
    }
}
