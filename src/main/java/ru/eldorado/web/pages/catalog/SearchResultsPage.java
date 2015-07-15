package ru.eldorado.web.pages.catalog;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.catalog.CatalogGoodItem;
import ru.eldorado.web.elements.catalog.CatalogGoodList;
import ru.eldorado.web.pages.MainPage;
import ru.eldorado.web.pages.WithNavigationPage;

/**
 * Created by OsipovI on 03.07.2015.
 */
public class SearchResultsPage extends WithNavigationPage {

    @FindBy (how = How.CSS, css = ".goodsList")
    public CatalogGoodList goodsList;

    @FindBy (how = How.CSS, css = ".item")
    public CatalogGoodItem goodItem;
}
