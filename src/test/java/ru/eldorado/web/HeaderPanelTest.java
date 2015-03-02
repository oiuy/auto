package ru.eldorado.web;

import org.junit.Test;
import ru.eldorado.web.pages.MainPage;

public class HeaderPanelTest extends AbstractSeleniumTest {
    @Test
    public void shopAddresses() {
        MainPage mainPage = openPageAndRightRegion("/", MainPage.class);
        mainPage.headerPanel.headerMenu.goToShopAddresses();
        checkRedirect("/A716/ru/info/shops/62000001000/");
    }

    @Test
    public void delivery() {
        MainPage mainPage = openPageAndRightRegion("/", MainPage.class);
        mainPage.headerPanel.headerMenu.goToDelivery();
        checkRedirect("/A716/ru/help/delivery/");
    }
}
