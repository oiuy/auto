package ru.eldorado.web.elements.checkout.delivery;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

import java.util.List;

/**
 * Created by OsipovI on 23.07.2015.
 */
public class DeliveryCalendar extends AbstractElement{
    @FindBy(how = How.CSS, css = ".roundedSelectItem")
    private List<SelenideElement> calendar;
}
