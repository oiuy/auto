package ru.eldorado.web.popups;

import com.codeborne.selenide.ElementsContainer;

public abstract class AbstractPopup extends ElementsContainer {
    public boolean isDisplayed() {
        return getSelf().isDisplayed();
    }
}
