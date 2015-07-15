package ru.eldorado.web.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPage extends WithNavigationPage {

    @FindBy(how = How.ID, id = "search_line")
    private SelenideElement searchField;

    @FindBy(how = How.CSS, css = ".headerSearchSubmit")
    private SelenideElement searchButton;

    public void findItem (String searchText){
        this.searchField.setValue(searchText);
        searchButton.click();
    }
}
