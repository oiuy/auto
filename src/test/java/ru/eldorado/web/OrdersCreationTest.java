package ru.eldorado.web;

import org.junit.After;
import org.junit.Test;
import ru.eldorado.web.pages.MainPage;
import ru.eldorado.web.pages.catalog.SearchResultsPage;
import ru.eldorado.web.pages.checkout.*;
import ru.eldorado.web.utils.NumberWriter;

import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertFalse;

/**
 * Created by OsipovI on 21.08.2015.
 */
public class OrdersCreationTest extends AbstractSeleniumTest {

    private final static String ITEM_ID = "71078433";
    private final static String ITEM_ID2 = "71055750";
    private final static String ITEM_ID3 = "71079255";
    private final static String ITEM_ID4 = "71042152"; // express-service
    private final static String CITY = "Москва";
    private final static String SHOP_ADDRESS = "Люблинская";
    private final static String FIRST_NAME = "Имя";
    private final static String LAST_NAME = "Автотест";
    private final static String PHONE_CODE = "900";
    private final static String PHONE_NUM = "9012233";
    private final static String EMAIL = "999test@mail.com";
    private final static String OUT_FILE = "G://Отделы/IT/ОПП/Группа тестирования/Autotest_orders/test_orders.txt";

    private static String ORDER_NUM = "";

    public String getDate () {
        Calendar cal = new GregorianCalendar();
        cal.add(cal.DAY_OF_MONTH, 1);
        return String.valueOf(cal.DAY_OF_MONTH);
    }

    //Заказ с доставкой
    @Test
    public void orderWithDelivery () throws InterruptedException {
        MainPage mainPage = openPageAndRightRegion("/", MainPage.class);
        mainPage.findItem(ITEM_ID);

        SearchResultsPage resultsPage = pageByClass(SearchResultsPage.class);
        resultsPage.goodsList.addFirstToCart();
        resultsPage.findItem(ITEM_ID2);
        resultsPage.goodsList.addFirstToCart();
        resultsPage.findItem(ITEM_ID3);
        resultsPage.goodsList.addFirstToCart();
        resultsPage.findItem(ITEM_ID4);
        resultsPage.goodsList.addFirstToCart();

        assertFalse(resultsPage.headerLogoBlock.miniCartBitrix.isBasketEmpty());
        resultsPage.headerLogoBlock.miniCartBitrix.goToCart();
        urlContains("/personal/basket");

        CartPage cartPage = pageByClass(CartPage.class);
        cartPage.cartGoodList.setProductCount(ITEM_ID, "3");
        cartPage.deliveryBox.chooseDelivery();
        cartPage.cartTotalPart.checkout();

        AuthorizationPage authorizationPage = pageByClass(AuthorizationPage.class);
        authorizationPage.buyWithoutRegistration();

        DeliveryPage deliveryPage = pageByClass(DeliveryPage.class);

        deliveryPage.selectDate(getDate());
        deliveryPage.selectTime("15:00");
        deliveryPage.metro.setValue("Речной вокзал");
        deliveryPage.street.setValue("Смольная");
        deliveryPage.house.setValue("12");
        deliveryPage.flat.setValue("101");
        deliveryPage.firstName.setValue(FIRST_NAME);
        deliveryPage.lastName.setValue(LAST_NAME);
        deliveryPage.phoneCode.setValue(PHONE_CODE);
        deliveryPage.phoneNumber.setValue(PHONE_NUM);
        deliveryPage.email.setValue(EMAIL);
        deliveryPage.submit();

        PaymentPage paymentPage = pageByClass(PaymentPage.class);
        paymentPage.selectCash();
        paymentPage.submit();

        SummaryPage summaryPage = pageByClass(SummaryPage.class);
        summaryPage.submit();
        ThankYouPage thankYouPage = pageByClass(ThankYouPage.class);
        ORDER_NUM = thankYouPage.getOrderNumberBitrix();
    }

    //Заказ самовывоз
    @Test
    public void orderPickup () throws InterruptedException {
        MainPage mainPage = openPageAndRightRegion("/", MainPage.class);
        mainPage.findItem(ITEM_ID);

        SearchResultsPage resultsPage = pageByClass(SearchResultsPage.class);
        resultsPage.goodsList.addFirstToCart();
        resultsPage.findItem(ITEM_ID2);
        resultsPage.goodsList.addFirstToCart();
        resultsPage.findItem(ITEM_ID3);
        resultsPage.goodsList.addFirstToCart();
        resultsPage.findItem(ITEM_ID4);
        resultsPage.goodsList.addFirstToCart();

        assertFalse(resultsPage.headerLogoBlock.miniCartBitrix.isBasketEmpty());
        resultsPage.headerLogoBlock.miniCartBitrix.goToCart();
        urlContains("/personal/basket");

        CartPage cartPage = pageByClass(CartPage.class);
        cartPage.cartGoodList.setProductCount(ITEM_ID, "3");
        cartPage.cartGoodList.selectAdditionalService(ITEM_ID4, "serv"); //добавляем Экспресс-сервис к заказу. Если он не нужен - комментируем строку
        cartPage.deliveryBox.choosePickUp();
        cartPage.deliveryBox.selectFrame.click();
        cartPage.deliveryBox.cityList.findCity(CITY);
        cartPage.cartTotalPart.checkout();

        AuthorizationPage authorizationPage = pageByClass(AuthorizationPage.class);
        authorizationPage.buyWithoutRegistration();

        PickupPage pickupPage = pageByClass(PickupPage.class);
        pickupPage.findShop(SHOP_ADDRESS);
        pickupPage.shopViewBitrix.collectFromHereClick();

        SummaryPage summaryPage = pageByClass(SummaryPage.class);
        summaryPage.setFirstName(FIRST_NAME);
        summaryPage.setLastName(LAST_NAME);
        summaryPage.setPhoneCode(PHONE_CODE);
        summaryPage.setPhoneNumber(PHONE_NUM);
        summaryPage.setEmail(EMAIL);
        summaryPage.submit();

        ThankYouPage thankYouPage = pageByClass(ThankYouPage.class);
        ORDER_NUM = thankYouPage.getOrderNumberBitrix();
    }

   @After
    public void printOut () throws FileNotFoundException {
        NumberWriter writer = new NumberWriter();
        writer.writeOut(OUT_FILE, ORDER_NUM);
    }
}
