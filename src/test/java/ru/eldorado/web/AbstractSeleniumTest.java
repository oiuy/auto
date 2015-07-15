package ru.eldorado.web;

import com.codeborne.selenide.junit.BrowserStrategy;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.eldorado.web.conditions.CheckRedirect;
import ru.eldorado.web.conditions.UrlContains;
import ru.eldorado.web.pages.AbstractPage;
import ru.eldorado.web.pages.WithNavigationPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public abstract class AbstractSeleniumTest {

    public static final int TIME_OUT = 30;

    @Rule
    public BrowserStrategy browserStrategy = new BrowserStrategy();

    @Before
    public void before() {

    }

    @After
    public void after() {

    }

    public <T> T openPageAndRightRegion(String relativeOrAbsoluteUrl, Class<? extends WithNavigationPage> pageObjectClassClass) {
        WithNavigationPage page = openPage(relativeOrAbsoluteUrl, pageObjectClassClass);
        if (page.headerPanel.headerRegion.regionConfirmPopup.isDisplayed()) {
            page.headerPanel.headerRegion.regionConfirmPopup.clickYes();
        }

        return (T) page;
    }

    public <T> T openPage(String relativeOrAbsoluteUrl, Class<? extends AbstractPage> pageObjectClassClass) {
        return (T) open(relativeOrAbsoluteUrl, pageObjectClassClass);
    }

    public <T> T pageByClass(Class<? extends AbstractPage> pageObjectClassClass) {
        return (T) page(pageObjectClassClass);
    }

    public void checkRedirect(String expectUrl) {
        driverWait().until(CheckRedirect.urlContains(expectUrl));
    }

    public void urlContains(String expectUrl) {
        driverWait().until(UrlContains.urlContains(expectUrl));
    }

    public WebDriverWait driverWait() {
        return new WebDriverWait(getWebDriver(), TIME_OUT);
    }

    public WebDriver getDriver() {
        WebDriver driver = getWebDriver();
        return driver;
    }
}
