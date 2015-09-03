package ru.eldorado.web;

import org.junit.Test;
import ru.eldorado.web.core.Order;
import ru.eldorado.web.elements.catalog.CatalogGoodItem;
import ru.eldorado.web.pages.MainPage;
import ru.eldorado.web.pages.catalog.SubCategoryPage;
import ru.eldorado.web.pages.checkout.*;
import ru.eldorado.web.vjdbc.Searcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class OrderPickUpCreationTest extends AbstractSeleniumTest {

    private final static String CATEGORY = "Техника для дома";
    private final static String SUBCATEGORY = "Стиральные машины";
    private final static String DELIVERY_FILTER = "Доступные для самовывоза";

    private final static String USER_NAME = "Autotest";
    private final static String USER_LAST_NAME = "Autotest";
    private final static String USER_PHONE_CODE = "903";
    private final static String USER_PHONE = "4071769";
    private final static String USER_EMAIL = "autotest@autotest.ru";

    @Test
    public void pickUpOrderCreation() throws InterruptedException {
        Order order = Order.getInstance();

        // переходим в категорию
        MainPage mainPage = openPageAndRightRegion("/", MainPage.class);
       // String categoryUrl = mainPage.catalogNav.subCategoryClick(CATEGORY, SUBCATEGORY);
        //checkRedirect(categoryUrl);

        // получаем Page Object категории
        SubCategoryPage subCategoryPage = pageByClass(SubCategoryPage.class);

        // применяем фильтр "Доступны в доставку"
        subCategoryPage.filter.applyFilter(DELIVERY_FILTER);

        // складываем первый продукт из списка в корзину и получаем его
        CatalogGoodItem product = subCategoryPage.filteredGoodList.addFirstToCart();
        order.setAttribute(Order.PRODUCTID, product.productCode());

        // проверяем пуста ли корзина
        assertFalse(subCategoryPage.headerLogoBlock.miniCart.isBasketEmpty());

        // проверяем, что сумма в корзине ровна цене добавленного товара
        assertEquals(product.getPrice(), subCategoryPage.headerLogoBlock.miniCart.basketCost());

        // переходим в корзину
        subCategoryPage.headerLogoBlock.miniCart.goToCart();
        urlContains("/ru/cart");

        // получаем Page Object корзины
        CartPage cartPage = pageByClass(CartPage.class);
        cartPage.deliveryBox.choosePickUp();

        // заполняем данные о заказе
        order.setAttribute(Order.DELIVERYCOST, Integer.toString(cartPage.cartTotalPart.deliveryCost()));
        order.setAttribute(Order.SUBTOTAL, Integer.toString(cartPage.cartTotalPart.productsPrice()));
        order.setAttribute(Order.DELIVERYMODE, "pickup");
        order.setAttribute(Order.TOTALPRICE, Integer.toString(cartPage.cartTotalPart.totalPrice()));
        order.setAttribute(Order.PRODUCTNUMBER, Integer.toString(cartPage.cartGoodList.numberOfProduct(order.getAttribute(Order.PRODUCTID))));

        // нажимаем кнопку "Оформить заказ"
        cartPage.cartTotalPart.checkout();
        urlContains("/ru/checkout/multi/authorization");

        // получаем Page Object страницы авторизации
        AuthorizationPage authorizationPage = pageByClass(AuthorizationPage.class);

        // нажимаем купить без регистрации
      //  authorizationPage.buyWithoutRegistration();
        urlContains("/ru/checkout/multi/choose-pickup-location");

        // получаем Page Object страницы выбора места самовывоза
        PickupPage pickupPage = pageByClass(PickupPage.class);
        assertEquals("62000001000", pickupPage.citySelect.getValue());

        // выбираем розничный магазин
        pickupPage.shopList.selectAddress("Московское ш., д. 65А");

        // проверяем правильность выбора
        assertEquals("Московское ш., д. 65А", pickupPage.shopView.getName());

        // нажимаем "Заберу отсюда"
        pickupPage.shopView.collectFromHereClick();
        urlContains("/ru/checkout/multi/summary");

        // получаем Page Object страницы подтверждения заказа
        SummaryPage summaryPage = pageByClass(SummaryPage.class);

        // заполняем данные формы
        summaryPage.setFirstName(USER_NAME);
        summaryPage.setLastName(USER_LAST_NAME);
        order.setAttribute(Order.USERNAME, USER_NAME + " " + USER_LAST_NAME);

        summaryPage.setPhoneCode(USER_PHONE_CODE);
        summaryPage.setPhoneNumber(USER_PHONE);
        order.setAttribute(Order.USERPHONE, USER_PHONE_CODE + USER_PHONE);

        summaryPage.setEmail(USER_EMAIL);
        order.setAttribute(Order.USEREMAIL, USER_EMAIL);

        // отправляем форму
        summaryPage.submit();

        // получаем Page Object страницы спасибо за покупку
        ThankYouPage thankYouPage = pageByClass(ThankYouPage.class);

        // заполняем данные о заказе
        order.setAttribute(Order.ID, thankYouPage.getOrderNumber());
        order.setAttribute(Order.STATUS, Order.Status.RECEIVED.toString());

        // Проверяем правильность созданного заказа
        new Searcher().isObjectRightCreated(order);
    }

}
