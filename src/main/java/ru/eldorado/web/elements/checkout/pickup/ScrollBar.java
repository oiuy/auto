package ru.eldorado.web.elements.checkout.pickup;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Created by OsipovI on 10.07.2015.
 */
public class ScrollBar extends AbstractElement {

    @FindBy (how = How.CSS, css = ".thumb")
    private SelenideElement thumb;

    private WebDriver driver = getWebDriver();

    public void scrollDown() {
        Actions actions = new Actions(driver);
        int x = thumb.getCoordinates().inViewPort().getX();
        int y = thumb.getCoordinates().inViewPort().getY()-213;
        actions.clickAndHold(thumb).build().perform();
        actions.moveByOffset(x,y).build().perform();
        actions.release(thumb).build().perform();
    }

    public void scrollUp() {
        Actions actions = new Actions(driver);
        int x = thumb.getCoordinates().inViewPort().getX();
        int y = thumb.getCoordinates().inViewPort().getY()-420;
        actions.clickAndHold(thumb).build().perform();
        actions.moveByOffset(x,y).build().perform();
        actions.release(thumb).build().perform();
    }

}
