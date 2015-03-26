package ru.eldorado.web.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.header.HeaderLogoBlock;
import ru.eldorado.web.elements.header.HeaderNav;
import ru.eldorado.web.elements.header.HeaderPanel;
import ru.eldorado.web.elements.main.MainSection;

public class AbstractPage {
    @FindBy(how = How.CSS, css = ".headerPanel")
    public HeaderPanel headerPanel;

    @FindBy(how = How.CSS, css = ".headerNav")
    public HeaderNav headerNav;

    @FindBy(how = How.CSS, css = ".headerLogoBlock")
    public HeaderLogoBlock headerLogoBlock;
}
