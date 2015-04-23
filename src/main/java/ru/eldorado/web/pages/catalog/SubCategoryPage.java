package ru.eldorado.web.pages.catalog;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.catalog.CatalogFilter;
import ru.eldorado.web.elements.catalog.CatalogGoodList;
import ru.eldorado.web.pages.WithNavigationPage;

public class SubCategoryPage extends WithNavigationPage {
    @FindBy(how = How.ID, id = "filtered-goods-list")
    public CatalogGoodList filteredGoodList;

    @FindBy(how = How.CSS, css = ".catalogSectionFilter")
    public CatalogFilter filter;
}
