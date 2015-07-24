package ru.eldorado.web.pages;

import com.codeborne.selenide.SelenideElement;
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

    @FindBy(how = How.ID, id = "search_line")
    private SelenideElement searchField;

    @FindBy(how = How.CSS, css = ".headerSearchSubmit")
    private SelenideElement searchButton;

    public void findItem (String searchText){
        this.searchField.setValue(searchText);
        searchButton.click();
    }
}
