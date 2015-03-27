package ru.eldorado.web.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.eldorado.web.core.Order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class SubCategoryPage extends AbstractPage {
    @FindBy(how = How.CSS, css = ".leftColumn")
    private SelenideElement filters;

    @FindBy(how = How.ID, id = "filtered-goods-list")
    private SelenideElement filteredGoodList;

    private static final String ADD_TO_CART = "addToCart";

    private static final String INCART = "В корзине";

    public void applyFilter(String name){
        filters.findElementByLinkText(name).click();
    }

    public String addToCartAndGoToCartPage() throws Exception{
        filteredGoodList.findElement(By.className("goodsInSection")).
                findElement(By.className("onPageShowItemRight")).click();
        List<WebElement> goods = filteredGoodList.findElements(By.className("item"));
        int prevCount = headerLogoBlock.basketCount();
        int prevCost = headerLogoBlock.basketCost();
        WebElement product = goods.get(0);
        product.findElement(By.className(ADD_TO_CART)).click();
        (new WebDriverWait(getWebDriver(), 20))
                .until(ExpectedConditions.visibilityOf(headerLogoBlock.getBasketNotEmpty()));
        int currCount = headerLogoBlock.basketCount();
        int currCost = headerLogoBlock.basketCost();
        if (isProductAddToCart(prevCount, currCount, prevCost, currCost, product)) {
            Order order = Order.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            order.setAttribute(Order.CREATEDATE, dateFormat.format(date).toString());
            order.setAttribute(Order.PRODUCTID, productCode(product));
            order.setAttribute(Order.PRODUCTPRICE, productPrice(product));
            order.setAttribute(Order.PRODUCTNAME, product.findElement(By.className("itemTitle")).getText().trim());
            product.findElement(By.className(ADD_TO_CART)).click();
            return url();
        }
        else {
            throw new Exception("The product isn't added to cart. Something goes wrong...");
        }
    }

    private String productCode(WebElement product){
        return product.findElement(By.className(ADD_TO_CART)).getAttribute("data-product");
    }

    private String productPrice(WebElement product){
        return product.findElement(By.className("itemPriceNew")).getText().replaceAll("[^0-9]", "");
    }

    private boolean isProductAddToCart
            (int prevCount, int currCount, int prevCost, int currCost, WebElement product){
        return ((currCount-prevCount)==1) &&
                (currCost-prevCost) == Integer.valueOf(productPrice(product)) &&
                product.findElement(By.className("addToCartCP")).getText().equals(INCART);
    }
}
