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

public class PickUpOrderBitrix extends AbstractSeleniumTest {

    private final static String ITEM_ID = "71055750";
    private final static String ITEM_ID2 = "71042152";
    private final static String USER_NAME = "";
    private final static String PASSWORD = "";
    private final static String CITY = "Москва";
    private final static String SHOP_ADDRESS = "ТК «Л-153», Братиславская, ул. Люблинская, д.153";
    private final static String FIRST_NAME = "Иван";
    private final static String LAST_NAME = "Автотест";
    private final static String PHONE_CODE = "900";
    private final static String PHONE_NUM = "9012233";
    private final static String EMAIL = "test@mail.com";
    private final static String OUT_FILE = "C://Users/Osipovi/Desktop/out.txt";

    private static String ORDER_NUM = "";

    @Test //Самовывоз с локального склада:
    public void pickupOrderCreation() throws InterruptedException {

        //Главная страница
        MainPage mainPage = openPageAndRightRegion("/", MainPage.class);
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
        //выбираем вариант получения товара:
        cartPage.deliveryBox.choosePickUp(); //самовывоз
        //cartPage.deliveryBox.chooseDelivery(); //доставка
        //выбираем город для самовывоза
        cartPage.deliveryBox.selectCity(CITY);
        //жмем кнопку "Оформить заказ"
        cartPage.cartTotalPart.checkout();
        urlContains("personal/order");

        //Страница авторизации
        AuthorizationPage authorizationPage = pageByClass(AuthorizationPage.class);
        //Войти:
        //authorizationPage.login(USER_NAME , PASSWORD);
        //Купить без авторизации:
        authorizationPage.setFocusOnRightBock();
        authorizationPage.buyWithoutRegistration();
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

    @After
    public void printOut () throws FileNotFoundException {
        NumberWriter writer = new NumberWriter();
        writer.writeOut(OUT_FILE, ORDER_NUM);
    }

}
