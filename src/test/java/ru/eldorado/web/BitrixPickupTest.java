package ru.eldorado.web;
/**
 * Created by OsipovI on 03.07.2015.
 */

import org.junit.After;
import org.junit.Test;
import ru.eldorado.web.elements.catalog.CatalogGoodItem;
import ru.eldorado.web.pages.MainPage;
import ru.eldorado.web.pages.catalog.SearchResultsPage;
import ru.eldorado.web.pages.checkout.*;
import ru.eldorado.web.utils.NumberWriter;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class BitrixPickupTest extends AbstractSeleniumTest {

    private final static String ITEM_ID = "71055750";
    //private final static String ITEM_ID2 = "71042152";
    private final static String ITEM_US = "71081676";
    private final static String ITEM_US2 = "71083085";
    private final static String USER_NAME = "";
    private final static String PASSWORD = "";
    private final static String CITY = "Москва";
    private final static String SHOP_ADDRESS = "ул. Люблинская, д.153";
    private final static String FIRST_NAME = "Авто";
    private final static String LAST_NAME = "Тест";
    private final static String PHONE_CODE = "900";
    private final static String PHONE_NUM = "9012233";
    private final static String EMAIL = "999test@mail.com";
    //private final static String OUT_FILE = "G://Отделы/IT/ОПП/Группа тестирования/Autotest_orders/orders.txt";
    private final static String OUT_FILE = "C://Users/Osipovi/Desktop/out.txt";

    private static String ORDER_NUM = "";

    @Test //Самовывоз с локального склада:
    public void pickupOrderCreation() throws InterruptedException {

        //Главная страница
        MainPage mainPage = openPageAndRightRegion("/", MainPage.class);
        //Выбор другого региона:
        /*MainPage mainPage = openPage("/", MainPage.class);
        mainPage.headerPanel.headerRegion.regionConfirmPopup.clickNo();
        mainPage.selectRegion("Волгоград");*/
        //ищем товар по н/н
        mainPage.findItem(ITEM_ID);

        //Страница с результатами поиска
        SearchResultsPage resultsPage = pageByClass(SearchResultsPage.class);
        //добавляем товар в корзину
        CatalogGoodItem product = resultsPage.goodsList.addFirstToCart();
        int itemsPrice = product.getPrice(); //запоминаем цену товара

        //Чтобы найти и добавить в корзину ещё один товар:
      /*  resultsPage.findItem(ITEM_ID2); // ищем товар по н/н
        product = resultsPage.goodsList.addFirstToCart(); // добавляем его в корзину
        itemsPrice = itemsPrice + product.getPrice(); //добавляем цену товара в сумму заказа*/

        //проверяем, пуста ли корзина
        assertFalse(resultsPage.headerLogoBlock.miniCartBitrix.isBasketEmpty());
        //сверяем сумму корзины и стоимость товара
        assertEquals(itemsPrice, resultsPage.headerLogoBlock.miniCartBitrix.basketCost());
        // переходим в корзину
        resultsPage.headerLogoBlock.miniCartBitrix.goToCart();
        urlContains("/personal/basket");

        //Корзина
        CartPage cartPage = pageByClass(CartPage.class);
        cartPage.cartGoodList.setProductCount(ITEM_ID, "3"); //Выбираем количество товара ITEM_ID
        //Выбираем вариант получения товара:
        cartPage.deliveryBox.choosePickUp(); //Самовывоз
        //выбираем город для самовывоза
        cartPage.deliveryBox.cityList.findCity(CITY);
        //жмем кнопку "Оформить заказ"
        cartPage.cartTotalPart.checkout();
        urlContains("personal/order");

        //Страница авторизации:
        AuthorizationPage authorizationPage = pageByClass(AuthorizationPage.class);
        //Купить без авторизации
        authorizationPage.buyWithoutRegistration(); //нажали кнопку "Купить без авторизации"
        //проверяем редирект на страницу выбора магазина:
        urlContains("personal/order_self_delivery"); //если самовывоз
        //Страница выбора магазина
        PickupPage pickupPage = pageByClass(PickupPage.class);
        pickupPage.findShop(SHOP_ADDRESS); //выбираем нужный магазин по адресу
        pickupPage.shopViewBitrix.collectFromHereClick(); //жмем кнопку "Заберу отсюда"
        urlContains("step=sd_confirm");

        //Страница подтверждения
        SummaryPage summaryPage = pageByClass(SummaryPage.class);
        //Заполняем поля
        summaryPage.setFirstName(FIRST_NAME);
        summaryPage.setLastName(LAST_NAME);
        summaryPage.setPhoneCode(PHONE_CODE);
        summaryPage.setPhoneNumber(PHONE_NUM);
        summaryPage.setEmail(EMAIL);
        //Жмем "Подтвердить"
        summaryPage.submit();

        //Последняя страница
        ThankYouPage thankYouPage = pageByClass(ThankYouPage.class);
        ORDER_NUM = thankYouPage.getOrderNumberBitrix();
    }

    @Test //самовывоз с УС:
    public void pickupUSCreation () throws InterruptedException {
        MainPage mainPage = openPageAndRightRegion("/", MainPage.class);
        mainPage.findItem(ITEM_US);

        SearchResultsPage searchResultsPage = pageByClass(SearchResultsPage.class);
        CatalogGoodItem product = searchResultsPage.goodsList.addFirstToCart();
        int itemsPrice = product.getPrice();
        searchResultsPage.findItem(ITEM_US2);
        product = searchResultsPage.goodsList.addFirstToCart();
        itemsPrice = itemsPrice + product.getPrice();
        assertFalse(searchResultsPage.headerLogoBlock.miniCartBitrix.isBasketEmpty());
        assertEquals(itemsPrice, searchResultsPage.headerLogoBlock.miniCartBitrix.basketCost());
        searchResultsPage.headerLogoBlock.miniCartBitrix.goToCart();
        urlContains("/personal/basket");

        CartPage cartPage = pageByClass(CartPage.class);
        cartPage.cartGoodList.setProductCount(ITEM_US, "2");
        cartPage.cartGoodList.selectAdditionalService(ITEM_US, "express");
        cartPage.cartGoodList.setProductCount(ITEM_US2, "2");
        cartPage.deliveryBox.choosePickUp();
        cartPage.deliveryBox.cityList.findCity(CITY);
        cartPage.cartTotalPart.checkout();
        urlContains("personal/order");

        AuthorizationPage authorizationPage = pageByClass(AuthorizationPage.class);
        authorizationPage.buyWithoutRegistration(); //нажали кнопку "Купить без авторизации"
        urlContains("personal/order_self_delivery");

        PickupPage pickupPage = pageByClass(PickupPage.class);
        pickupPage.findShop(SHOP_ADDRESS);
        pickupPage.shopViewBitrix.collectFromHereClick(); //жмем кнопку "Заберу отсюда"
        urlContains("step=sd_confirm");

        SummaryPage summaryPage = pageByClass(SummaryPage.class);
        summaryPage.setFirstName(FIRST_NAME);
        summaryPage.setLastName(LAST_NAME);
        summaryPage.setPhoneCode(PHONE_CODE);
        summaryPage.setPhoneNumber(PHONE_NUM);
        summaryPage.setEmail(EMAIL);
        summaryPage.submit();

        //Последняя страница
        ThankYouPage thankYouPage = pageByClass(ThankYouPage.class);
        ORDER_NUM = thankYouPage.getOrderNumberBitrix();

    }

    @After
    public void printOut () throws FileNotFoundException {
        NumberWriter writer = new NumberWriter();
        writer.writeOut(OUT_FILE, ORDER_NUM);
    }

}
