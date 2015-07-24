package ru.eldorado.web.pages.catalog;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.catalog.CatalogGoodItem;
import ru.eldorado.web.elements.catalog.CatalogGoodList;
import ru.eldorado.web.pages.WithNavigationPage;

public class SearchResultsPage extends WithNavigationPage {

    @FindBy (how = How.CSS, css = ".goodsList")
    public CatalogGoodList goodsList;

    @FindBy (how = How.CSS, css = ".item")
    public CatalogGoodItem goodItem;
}
