package ru.eldorado.web.elements.header.catalog;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;

public class CatalogNav extends AbstractElement {
    @FindBy(how = How.CSS, css = "ul.headerCatalogList")
    private SelenideElement list;

    @FindBy(how = How.CSS, css = "ul.headerCatalogList li.headerCatalogItem")
    private List<CatalogItem> items;

    @FindBy(how = How.CSS, css = ".headerCatalogHd")
    private SelenideElement header;

    public void dropDownCatalog() {
        header.hover();
        for (CatalogItem item : items) {
            item.getSelf().shouldBe(visible);
        }
    }

    public CatalogItem showSubCategories(String categoryName) {
        dropDownCatalog();

        CatalogItem item = findCategoryByTitle(categoryName);
        item.hover();
        item.assertSubCategoryVisible();

        return item;
    }

    public String subCategoryPageUrl(String categoryName, String subCategoryName) {
        return showSubCategories(categoryName).getLinkByName(subCategoryName);
    }

    public String subCategoryClick(String categoryName, String subCategoryName) {
        return showSubCategories(categoryName).linkClickByName(subCategoryName);
    }

    private CatalogItem findCategoryByTitle(String title) {
        CatalogItem result = null;
        for (CatalogItem item : items) {
            if (item.isTitle(title)) {
                result = item;
                break;
            }
        }

        if (result == null) {
            throw new AssertionError("Can't find category by name: " + title);
        }

        return result;
    }
}
