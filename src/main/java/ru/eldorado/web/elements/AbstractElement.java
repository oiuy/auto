package ru.eldorado.web.elements;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.SelenideFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractElement extends ElementsContainer {
    public <T extends AbstractElement> T initContainer(SelenideElement element, Class<? extends AbstractElement> type) {
        T result;
        try {
            result = (T) type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create elements container for element", e);
        }
        PageFactory.initElements(new SelenideFieldDecorator(element), result);
        result.setSelf(element);

        return result;
    }
}
