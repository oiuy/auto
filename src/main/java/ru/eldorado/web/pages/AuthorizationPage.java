package ru.eldorado.web.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.WebDriverRunner.url;

public class AuthorizationPage extends AbstractPage {
    @FindBy(how= How.ID, id = "orderAuthBlock")
    private SelenideElement authBlock;

    @FindBy(how= How.CSS, css = ".checoutBlockRight")
    private SelenideElement registrBlock;


    public String buyWithoutRegist(){
        registrBlock.findElement(By.id("order_no_registration")).click();
        return url();
    }
}
