package ru.eldorado.web.elements.checkout.delivery;

import com.codeborne.selenide.SelenideElement;
import org.apache.http.MessageConstraintException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

import java.util.List;

/**
 * Created by OsipovI on 23.07.2015.
 */
public class OptionsContainer extends AbstractElement{
    @FindBy(how = How.CSS, css = ".roundedSelectItemText")
    private List<SelenideElement> options;

    public void selectOption (String text){
        for (SelenideElement option : options){
            if (option.getText().contains(text)){
                option.click();
                return;
            }
        }
    }
}
