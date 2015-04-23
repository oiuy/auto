package ru.eldorado.web.conditions;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import static com.codeborne.selenide.WebDriverRunner.url;

public class CheckRedirect implements ExpectedCondition<Boolean> {
    private String string;

    public static CheckRedirect urlContains(String string) {
        return new CheckRedirect(string);
    }

    public CheckRedirect(String string) {
        this.string = string;
    }

    @Override
    public Boolean apply(WebDriver input) {
        String actualUrl = url();
        if (!string.startsWith("http:") && !string.startsWith("https:") && !string.startsWith("file:")) {
            string = Configuration.baseUrl + string;
        }

        return actualUrl.equals(string);
    }

    @Override
    public boolean equals(Object object) {
        return false;
    }
}
