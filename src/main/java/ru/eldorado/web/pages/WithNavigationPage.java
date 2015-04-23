package ru.eldorado.web.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.header.HeaderLogoBlock;
import ru.eldorado.web.elements.header.catalog.CatalogNav;
import ru.eldorado.web.elements.header.HeaderPanel;

public abstract class WithNavigationPage extends AbstractPage {
    @FindBy(how = How.CSS, css = ".headerPanel")
    public HeaderPanel headerPanel;

    @FindBy(how = How.CSS, css = ".headerNav")
    public CatalogNav catalogNav;

    @FindBy(how = How.CSS, css = ".headerLogoBlock")
    public HeaderLogoBlock headerLogoBlock;
}
