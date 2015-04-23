package ru.eldorado.web.elements.header.catalog;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

import java.util.List;

public class CatalogItem extends AbstractElement {
    @FindBy(how = How.CSS, css = "a.headerCatalogItemLink span.text")
    private SelenideElement title;

    @FindBy(how = How.CSS, css = "div.headerCatalogSub")
    private SelenideElement subCatalog;

    @FindBy(how = How.CSS, css = "div.headerCatalogSub ul.headerCatalogCol li a")
    private List<SelenideElement> links;

    public boolean isTitle(String titleText) {
        return title.text().equals(titleText);
    }

    public String getLinkByName(String name) {
        return byName(name).getAttribute("href");
    }

    public String linkClickByName(String name) {
        SelenideElement link = byName(name);
        link.click();
        return link.getAttribute("href");
    }

    public void hover() {
        getSelf().hover();
    }

    public void assertSubCategoryVisible() {
        subCatalog.shouldBe(Condition.visible);
    }

    private SelenideElement byName(String name) {
        for (SelenideElement link : links) {
            if (link.getText().equals(name)) {
                return link;
            }
        }

        throw new AssertionError("Can't find link by name: " + name);
    }
}
