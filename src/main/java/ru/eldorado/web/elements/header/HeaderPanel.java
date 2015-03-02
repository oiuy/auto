package ru.eldorado.web.elements.header;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

public class HeaderPanel extends AbstractElement {
    @FindBy(how = How.CSS, css = ".headerMenu")
    public HeaderMenu headerMenu;

    @FindBy(how = How.CSS, css = ".headerRegion")
    public HeaderRegion headerRegion;
}

