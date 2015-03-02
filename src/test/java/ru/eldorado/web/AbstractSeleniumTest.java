package ru.eldorado.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit.BrowserStrategy;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import ru.eldorado.web.pages.AbstractPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;

public abstract class AbstractSeleniumTest {

    @Rule
    public BrowserStrategy browserStrategy = new BrowserStrategy();

    @Before
    public void before() {

    }

    @After
    public void after() {

    }

    public <T> T openPageAndRightRegion(String relativeOrAbsoluteUrl, Class<? extends AbstractPage> pageObjectClassClass) {
        AbstractPage page = openPage(relativeOrAbsoluteUrl, pageObjectClassClass);
        if (page.headerPanel.headerRegion.regionConfirmPopup.isDisplayed()) {
            page.headerPanel.headerRegion.regionConfirmPopup.clickYes();
        }

        return (T) page;
    }

    public <T> T openPage(String relativeOrAbsoluteUrl, Class<? extends AbstractPage> pageObjectClassClass) {
        return (T) open(relativeOrAbsoluteUrl, pageObjectClassClass);
    }

    public void checkRedirect(String expectUrl) {
        String actualUrl = url();
        if (!expectUrl.startsWith("http:") && !expectUrl.startsWith("https:") && !expectUrl.startsWith("file:")) {
            expectUrl = Configuration.baseUrl + expectUrl;
        }
        assertEquals(expectUrl, actualUrl);
    }
}
