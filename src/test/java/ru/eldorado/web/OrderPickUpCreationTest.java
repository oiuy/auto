package ru.eldorado.web;

import org.junit.Test;
import ru.eldorado.web.pages.*;
import ru.eldorado.web.vjdbc.Searcher;

import static org.junit.Assert.assertTrue;

public class OrderPickUpCreationTest extends AbstractSeleniumTest {

    private final static String CATEGORY = "Техника для дома";
    private final static String SUBCATEGORY = "Стиральные машины";
    private final static String DELIVERYFILTER = "Доступные для самовывоза";

    private final static String USER_NAME = "Autotest";
    private final static String USER_LAST_NAME = "Autotest";
    private final static String USER_PHONE_CODE = "903";
    private final static String USER_PHONE = "4071769";
    private final static String USER_EMAIL = "autotest@autotest.ru";

    @Test
    public void pickUpOrderCreation(){
        MainPage mainPage = openPageAndRightRegion("/", MainPage.class);
        SubCategoryPage subCategoryPage = openPage(mainPage.headerNav.
                subCategoryPageUrl(CATEGORY,SUBCATEGORY), SubCategoryPage.class);
        subCategoryPage.applyFilter(DELIVERYFILTER);
        try {
            String cartPageUrl = subCategoryPage.addToCartAndGoToCartPage();
            CartPage cartPage = openPage(cartPageUrl, CartPage.class);
            assertTrue(cartPage.isPickUp());
            String authPageUrl = cartPage.checkOut();
            checkRedirect(authPageUrl);
            AuthorizationPage authPage = openPage(authPageUrl, AuthorizationPage.class);
            String pickUpLocPAgeUrl = authPage.buyWithoutRegist();
            PickupLocationPage pkPage = openPage(pickUpLocPAgeUrl, PickupLocationPage.class);
            String orderConfPageUrl = pkPage.chooseAnyShop();
            OrderConfirmationPage orderConfirmationPage = openPage(orderConfPageUrl, OrderConfirmationPage.class);
            String thankYouPageUrl = orderConfirmationPage.confirmOrder(USER_NAME, USER_LAST_NAME, USER_PHONE_CODE,
                    USER_PHONE, USER_EMAIL);
            checkRedirect(thankYouPageUrl);
            ThankYouPage thankYouPage = openPage(thankYouPageUrl, ThankYouPage.class);
            thankYouPage.finishOrderCreation();
            assertTrue(new Searcher().isObjectRightCreated());
        }
        catch (Exception e){
            System.out.println("The test is failed due to "+e.getMessage());
        }

    }

}
