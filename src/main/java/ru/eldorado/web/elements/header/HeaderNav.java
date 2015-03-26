package ru.eldorado.web.elements.header;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.eldorado.web.elements.AbstractElement;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class HeaderNav extends AbstractElement {
    @FindBy(how = How.CSS, css = ".headerCatalogList")
    private SelenideElement catalogList;

    private static final String CATALOG_LINK_CLASS = "headerCatalogHd";
    private static final String CATALOG_ITEM = "headerCatalogItem";
    private static final String SUBTATEGORY_MENU = "headerCatalogSub";
    private static final String CATEGORY_ITEM = "headerCatalogNib";
    private static final String REFERENCE = "href";


    public void dropDownCatalog(){
        WebElement catalogHeader = catalogList.findElement(By.className(CATALOG_LINK_CLASS));
        Actions action = new Actions(getWebDriver());
        action.moveToElement(catalogHeader).build().perform();
        (new WebDriverWait(getWebDriver(), 20))
                .until(ExpectedConditions.presenceOfElementLocated(By.className(CATEGORY_ITEM)));
    }

    public void showSubCategories(String categoryName){
        dropDownCatalog();
        if(isCategoryPresent(categoryName)) {
            WebElement category = catalogList.findElementByLinkText(categoryName);
            Actions action = new Actions(getWebDriver());
            action.moveToElement(category).build().perform();
            (new WebDriverWait(getWebDriver(), 20))
                    .until(ExpectedConditions.presenceOfElementLocated(By.className(SUBTATEGORY_MENU)));
        }
        else {
            System.out.println("There is no category such category");
        }
    }

    public boolean isCategoryPresent(String categoryName) {
        dropDownCatalog();
        List<WebElement> categories =  catalogList.findElements(By.className(CATALOG_ITEM));
        for(WebElement category: categories){
            if(category.getText().trim().equals(categoryName)) return true;
        }
        return false;
    }

    public String subCategoryPageUrl(String categoryName, String subCategoryName){
        showSubCategories(categoryName);
        return catalogList.findElementByLinkText(subCategoryName).getAttribute(REFERENCE);
    }
}
