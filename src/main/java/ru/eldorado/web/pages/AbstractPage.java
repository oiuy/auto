package ru.eldorado.web.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.header.HeaderPanel;

public class AbstractPage {
    @FindBy(how = How.CSS, css = ".headerPanel")
    public HeaderPanel headerPanel;
}
