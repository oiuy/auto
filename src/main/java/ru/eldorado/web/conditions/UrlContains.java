package ru.eldorado.web.conditions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import static com.codeborne.selenide.WebDriverRunner.url;

public class UrlContains implements ExpectedCondition<Boolean> {
    private String string;

    public static UrlContains urlContains(String string) {
        return new UrlContains(string);
    }

    public UrlContains(String string) {
        this.string = string;
    }

    @Override
    public Boolean apply(WebDriver input) {
        return url().contains(string);
    }

    @Override
    public boolean equals(Object object) {
        return false;
    }
}
