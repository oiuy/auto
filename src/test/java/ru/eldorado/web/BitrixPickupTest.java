package ru.eldorado.web;
/**
 * Created by OsipovI on 03.07.2015.
 */

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.eldorado.web.elements.catalog.CatalogGoodItem;
import ru.eldorado.web.pages.MainPage;
import ru.eldorado.web.pages.catalog.SearchResultsPage;
import ru.eldorado.web.pages.checkout.*;
import ru.eldorado.web.utils.NumberWriter;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class BitrixPickupTest extends AbstractSeleniumTest {

    private final static String ITEM_ID = "71078433";
    //private final static String ITEM_ID2 = "71042152";
    private final static String USER_NAME = "";
    private final static String PASSWORD = "";
    private final static String CITY = "������";
    private final static String SHOP_ADDRESS = "�������";
    private final static String FIRST_NAME = "����";
    private final static String LAST_NAME = "����";
    private final static String PHONE_CODE = "900";
    private final static String PHONE_NUM = "9012233";
    private final static String EMAIL = "999test@mail.com";
    private final static String OUT_FILE = "G://������/IT/���/������ ������������/Autotest_orders/orders.txt";
   // private final static String OUT_FILE = "C://Users/Osipovi/Desktop/out.txt";

    private static String ORDER_NUM = "";

    @Test //��������� � ���������� ������:
    public void pickupOrderCreation() throws InterruptedException {

        //������� ��������
        MainPage mainPage = openPageAndRightRegion("/", MainPage.class);
        //����� ������� �������:
        /*MainPage mainPage = openPage("/", MainPage.class);
        mainPage.headerPanel.headerRegion.regionConfirmPopup.clickNo();
        mainPage.selectRegion(CITY);*/
        //���� ����� �� �/�
        mainPage.findItem(ITEM_ID);

        //�������� � ������������ ������
        SearchResultsPage resultsPage = pageByClass(SearchResultsPage.class);
        //��������� ����� � �������
        CatalogGoodItem product = resultsPage.goodsList.addFirstToCart();
        int itemsPrice = product.getPrice(); //���������� ���� ������

        //����� ����� � �������� � ������� ��� ���� �����:
      /*  resultsPage.findItem(ITEM_ID2); // ���� ����� �� �/�
        product = resultsPage.goodsList.addFirstToCart(); // ��������� ��� � �������
        itemsPrice = itemsPrice + product.getPrice(); //��������� ���� ������ � ����� ������*/

        //���������, ����� �� �������
        assertFalse(resultsPage.headerLogoBlock.miniCartBitrix.isBasketEmpty());
        //������� ����� ������� � ��������� ������
        assertEquals(itemsPrice, resultsPage.headerLogoBlock.miniCartBitrix.basketCost());
        // ��������� � �������
        resultsPage.headerLogoBlock.miniCartBitrix.goToCart();
        urlContains("/personal/basket");

        //�������
        CartPage cartPage = pageByClass(CartPage.class);
        cartPage.cartGoodList.setProductCount(ITEM_ID, 2); //�������� ���������� ������ ITEM_ID
        //�������� ������� ��������� ������:
        cartPage.deliveryBox.choosePickUp(); //���������
        //�������� ����� ��� ����������:
        cartPage.deliveryBox.selectCity(CITY);
        //���� ������ "�������� �����"
        cartPage.cartTotalPart.checkout();
        urlContains("personal/order");

        //�������� �����������:
        AuthorizationPage authorizationPage = pageByClass(AuthorizationPage.class);
        //������ ��� �����������
       authorizationPage.noRegistration();
        //��������� �������� �� �������� ������ ��������:
        urlContains("personal/order_self_delivery"); //���� ���������
        driverWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("listWrapper")));
        //�������� ������ ��������
        PickupPage pickupPage = pageByClass(PickupPage.class);
        pickupPage.findShop(SHOP_ADDRESS); //�������� ������ ������� �� ������
        getDriver().findElement(By.cssSelector(".addressConfirmSubmitButton")).click(); //���� ������ "������ ������"
        urlContains("step=sd_confirm");

        //�������� �������������
        SummaryPage summaryPage = pageByClass(SummaryPage.class);
        //��������� ����
        summaryPage.setFirstName(FIRST_NAME);
        summaryPage.setLastName(LAST_NAME);
        summaryPage.setPhoneCode(PHONE_CODE);
        summaryPage.setPhoneNumber(PHONE_NUM);
        summaryPage.setEmail(EMAIL);
        //���� "�����������"
        summaryPage.submit();

        //��������� ��������
        ThankYouPage thankYouPage = pageByClass(ThankYouPage.class);
        Thread.sleep(1000);
        ORDER_NUM = thankYouPage.getOrderNumberBitrix();
    }

    @Test //��������� � ��, ����� ���������� ITEM_ID, ����� ����� ��� ������ �� ��
    public void pickupUSCreation () throws InterruptedException {
        MainPage mainPage = openPageAndRightRegion("/", MainPage.class);
        mainPage.findItem(ITEM_ID);

        SearchResultsPage searchResultsPage = pageByClass(SearchResultsPage.class);
        CatalogGoodItem product = searchResultsPage.goodsList.addFirstToCart();
        int itemsPrice = product.getPrice();
        assertFalse(searchResultsPage.headerLogoBlock.miniCartBitrix.isBasketEmpty());
        assertEquals(itemsPrice, searchResultsPage.headerLogoBlock.miniCartBitrix.basketCost());
        searchResultsPage.headerLogoBlock.miniCartBitrix.goToCart();
        urlContains("/personal/basket");

        CartPage cartPage = pageByClass(CartPage.class);
        cartPage.cartGoodList.setProductCount(ITEM_ID, 2);
        cartPage.deliveryBox.choosePickUp();
        cartPage.deliveryBox.selectCity(CITY);
        cartPage.cartTotalPart.checkout();
        urlContains("personal/order");

        AuthorizationPage authorizationPage = pageByClass(AuthorizationPage.class);
        authorizationPage.noRegistration(); //������ ������ "������ ��� �����������"
        urlContains("personal/order_self_delivery");
        driverWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("listWrapper")));

        PickupPage pickupPage = pageByClass(PickupPage.class);
        pickupPage.findShop(SHOP_ADDRESS);
        getDriver().findElement(By.cssSelector(".addressConfirmSubmitButton")).click(); //���� ������ "������ ������"
        urlContains("step=sd_confirm");

        SummaryPage summaryPage = pageByClass(SummaryPage.class);
        summaryPage.setFirstName(FIRST_NAME);
        summaryPage.setLastName(LAST_NAME);
        summaryPage.setPhoneCode(PHONE_CODE);
        summaryPage.setPhoneNumber(PHONE_NUM);
        summaryPage.setEmail(EMAIL);
        summaryPage.submit();

        //��������� ��������
        ThankYouPage thankYouPage = pageByClass(ThankYouPage.class);
        Thread.sleep(1000);
        ORDER_NUM = thankYouPage.getOrderNumberBitrix();
    }

    @After
    public void printOut () throws FileNotFoundException {
        NumberWriter writer = new NumberWriter();
        writer.writeOut(OUT_FILE, ORDER_NUM);
    }

}
