package ru.eldorado.web.pages;

import com.codeborne.selenide.SelenideElement;
import com.sun.scenario.effect.impl.prism.ps.PPSRenderer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.core.Order;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static com.codeborne.selenide.WebDriverRunner.url;

public class OrderConfirmationPage extends AbstractPage {
    @FindBy(how = How.CSS, css = ".basketBlock")
    private SelenideElement basketBlock;

    @FindBy(how = How.CSS, css = ".finalBlock")
    private SelenideElement finalBlock;

    @FindBy(how = How.CSS, css = ".confirmDetailsBlock")
    private SelenideElement userDetailsBlock;

    @FindBy(how = How.CSS, css =".check_btn")
    private SelenideElement confirmButton;

    @FindBy(how = How.CSS, css =".errorBox")
    private SelenideElement errorBox;

    private static final String PRODUCT_DETAILS_TAG = "basketBlockRow";

    private static final String DETAILS_TEXT = "paramZnLeft";
    private static final String DETAILS_VALUE = "paramZnRight";
    private static final String TOTAL_PRICE_VALUE = "paramZnRightTotal";
    private static final String USER_NAME_TAG = "USER_FACE_NAME";
    private static final String USER_LAST_NAME_TAG = "USER_FACE_LAST_NAME";
    private static final String USER_PHONE_CODE = "mobile_phone_code";
    private static final String USER_PHONE_PHONE = "mobile_phone_phone";
    private static final String USER_EMAIL = "USER_EMAIL_PICKUP";

    private static final String COUNT = "Позиций";
    private static final String DELIVERY = "Самовывоз|Доставка";
    private static final String TOTAL_PRICE = "К оплате";

    public boolean checkOrderCorrectness() {
        WebElement product = basketBlock.findElement(By.className(PRODUCT_DETAILS_TAG));
        Order order = Order.getInstance();
        boolean productsCorrectness = true;
        String productName = product.findElement(By.className("__nameProd")).getText().trim();
        String productPrice = product.findElement(By.className("__endPrice")).
                getText().trim().replaceAll("[^0-9]", "");
        String productNum = product.findElement(By.className("__priceAndCount")).getText().trim();
        productsCorrectness = productsCorrectness && productPrice.equals(order.getAttribute(Order.PRODUCTPRICE)) &&
                productNum.equals(order.getAttribute(Order.PRODUCTNUMBER)) && productName.equals(order.getAttribute(Order.PRODUCTNAME));
        return productsCorrectness;
    }

    public String confirmOrder(String name, String lastName,String phoneCode, String phone, String email)throws Exception{
        if (checkOrderCorrectness()) {
            Order order = Order.getInstance();
            WebElement userName = userDetailsBlock.findElement(By.id(USER_NAME_TAG));
            WebElement userLastName = userDetailsBlock.findElement(By.id(USER_LAST_NAME_TAG));
            WebElement userEmail = userDetailsBlock.findElement(By.id(USER_EMAIL));
            WebElement userPhonCode = userDetailsBlock.findElement(By.id(USER_PHONE_CODE));
            WebElement userPhone = userDetailsBlock.findElement(By.id(USER_PHONE_PHONE));
            userName.sendKeys(name);
            userEmail.sendKeys(email);
            userLastName.sendKeys(lastName);
            userPhonCode.sendKeys(phoneCode);
            userPhone.sendKeys(phone);
            order.setAttribute(Order.USERNAME, name + " " + lastName);
            order.setAttribute(Order.USERPHONE, phoneCode + phone);
            order.setAttribute(Order.USEREMAIL, email);
            confirmButton.click();
            if (errorBox.isDisplayed()) {
                throw new Exception("There is a error in user information input block on confirmation page");
            }
            return url();
        }
        else {
            throw new Exception("The order on order confirmation page is incorrect");
        }
    }

}
