package ru.eldorado.web;

import org.junit.After;
import org.junit.Test;
import ru.eldorado.web.elements.catalog.CatalogGoodItem;
import ru.eldorado.web.pages.MainPage;
import ru.eldorado.web.pages.catalog.SearchResultsPage;
import ru.eldorado.web.pages.checkout.*;
import ru.eldorado.web.utils.NumberWriter;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by OsipovI on 23.07.2015.
 */
public class BitrixDeliveryTest extends AbstractSeleniumTest {

    private final static String ITEM_ID = "71055750";
    private final static String FIRST_NAME = "Авто";
    private final static String LAST_NAME = "Тест";
    private final static String PHONE_CODE = "900";
    private final static String PHONE_NUM = "9012233";
    private final static String EMAIL = "999test@mail.com";
    //private final static String OUT_FILE = "G://Отделы/IT/ОПП/Группа тестирования/Autotest_orders/delivery.txt";
    private final static String OUT_FILE = "C://Users/Osipovi/Desktop/delivery.txt";

    private static String ORDER_NUM = "";

    @Test
    public void deliveryOrderCreation () throws InterruptedException {
        //Main page
        MainPage mainPage = openPageAndRightRegion("/", MainPage.class);
        mainPage.findItem(ITEM_ID);
        //Search page
        SearchResultsPage resultsPage = pageByClass(SearchResultsPage.class);
        CatalogGoodItem product = resultsPage.goodsList.addFirstToCart();
        int itemsPrice = product.getPrice();
        assertFalse(resultsPage.headerLogoBlock.miniCartBitrix.isBasketEmpty());
        assertEquals(itemsPrice, resultsPage.headerLogoBlock.miniCartBitrix.basketCost());
        resultsPage.headerLogoBlock.miniCartBitrix.goToCart();
        urlContains("/personal/basket");
        //Cart page
        CartPage cartPage = pageByClass(CartPage.class);
        cartPage.cartGoodList.setProductCount(ITEM_ID, "2");
        cartPage.deliveryBox.chooseDelivery(); //выбираем доставку
        cartPage.cartTotalPart.checkout();
        urlContains("personal/order");
        //Authorization page
        AuthorizationPage authorizationPage = pageByClass(AuthorizationPage.class);
        authorizationPage.buyWithoutRegistration();
        urlContains("step=address");
        //Delivery page
        DeliveryPage deliveryPage = pageByClass(DeliveryPage.class);
        deliveryPage.selectDate("27");
        deliveryPage.selectTime("15:00");
        deliveryPage.metro.setValue("Речной вокзал");
        deliveryPage.street.setValue("Смольная");
        deliveryPage.house.setValue("12");
        deliveryPage.flat.setValue("101");
        deliveryPage.selectLift("грузовой");
        deliveryPage.firstName.setValue(FIRST_NAME);
        deliveryPage.lastName.setValue(LAST_NAME);
        deliveryPage.phoneCode.setValue(PHONE_CODE);
        deliveryPage.phoneNumber.setValue(PHONE_NUM);
        deliveryPage.email.setValue(EMAIL);
        deliveryPage.submit();
        urlContains("step=payment");
        //Payment page
        PaymentPage paymentPage = pageByClass(PaymentPage.class);
        paymentPage.selectCash(); //выбираем оплату наличными
        paymentPage.submit();
        urlContains("step=order_confirm");
        //Summary page
        SummaryPage summaryPage = pageByClass(SummaryPage.class);
        summaryPage.submit();
        //Last page
        ThankYouPage thankYouPage = pageByClass(ThankYouPage.class);
        ORDER_NUM = thankYouPage.getOrderNumberBitrix();
    }

    @After
    public void printOut () throws FileNotFoundException {
        NumberWriter writer = new NumberWriter();
        writer.writeOut(OUT_FILE, ORDER_NUM);
    }

}
