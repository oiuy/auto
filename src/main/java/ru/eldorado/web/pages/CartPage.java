package ru.eldorado.web.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.core.Order;
import ru.eldorado.web.popups.cart.CartPopup;


import static com.codeborne.selenide.WebDriverRunner.url;

public class CartPage extends AbstractPage {
    @FindBy(how = How.ID, id = "delivery-box")
    private SelenideElement deliveryBox;

    @FindBy(how = How.CSS, css = ".basketBlockWrap")
    private SelenideElement basketBlock;

    @FindBy(how = How.CSS, css = ".cartTotalPart")
    private SelenideElement totalBlock;

    @FindBy(how = How.ID, id = "cartItemPopup")
    private CartPopup cartPopup;

    private static final String PICKUP = "Самовывоз";
    private static final String DELIVERY = "Доставка.*";
    private static final String FREE = "бесплатно";
    private static final String PRODUCTS_COST = "Товаров на";


    public boolean isPickUp(){
       return deliveryBox.findElement(By.className("checkedBlock")).
                findElement(By.className("delivery-label")).getText().equals(PICKUP);
    }

    public boolean isDelivery(){
        return deliveryBox.findElement(By.className("checkedBlock")).
                findElement(By.className("delivery-label")).getText().equals(DELIVERY);
    }

    public boolean isCartNotEmpty(){
        return basketBlock.findElements(By.className("basketBlockRow")).size() > 0;
    }

    public boolean isProductPresent(String productCode){
        try{
            basketBlock.findElement(By.id("bskIds_"+productCode));
            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    public int numberOfProduct(String productCode){
        if(isProductPresent(productCode)) {
            return Integer.valueOf(basketBlock.findElement(By.id("bskIds_" + productCode)).
                    findElement(By.className("countProd")).getAttribute("value"));
        }
        else return 0;
    }

    public int deliveryCost() throws Exception{
        if(totalBlock.findElement(By.className("totals-delivery-label")).getText().equals(PICKUP) &&
                totalBlock.findElement(By.id("deliv_price_id")).getText().equals(FREE)){
                return 0;
        }
        else if(totalBlock.findElement(By.className("totals-delivery-label")).getText().equals(DELIVERY)){
           return Integer.valueOf(totalBlock.findElement(By.id("deliv_price_id")).getText().replaceAll("[^0-9]", ""));
        }
        else {
            throw new Exception("Something is wrong with delivery part of total block");
        }
    }

    private String totalPrice(){
        return totalBlock.findElement(By.id("total_price_id")).getText().replaceAll("[^0-9]", "");
    }

    private String subTotalPrice(){
        return totalBlock.findElement(By.id("goods_val_tr")).getText().replaceAll("[^0-9]", "");
    }

    public boolean totalPriceCorrect(){
        try {
            int total = Integer.valueOf(totalPrice());
            return total == Integer.valueOf(Order.getInstance().getAttribute(Order.PRODUCTNUMBER)) *
                    Integer.valueOf(Order.getInstance().getAttribute(Order.PRODUCTPRICE)) + deliveryCost();
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean isPickUPCartCorrect(){
        try {

            return isCartNotEmpty() && isPickUp() && (deliveryCost() == 0) && totalPriceCorrect();
        }
        catch(Exception e){
            return false;
        }
    }

    public String checkOut(){
        try {
            if (isPickUPCartCorrect()) {
                Order order = Order.getInstance();
                order.setAttribute(Order.DELIVERYCOST, Integer.toString(deliveryCost()));
                order.setAttribute(Order.SUBTOTAL, subTotalPrice());
                order.setAttribute(Order.DELIVERYMODE, "pickup");
                order.setAttribute(Order.TOTALPRICE, totalPrice());
                totalBlock.findElement(By.className("successBttnRP")).click();
                if(cartPopup.isDisplayed()){
                    System.out.println("Cart popup: "+cartPopup.getText());
                }
                return url();
            }
        }
        catch (Exception e){
            System.out.println("Checkout can not be done due to "+e.getMessage());
        }
        return url();
    }
}
