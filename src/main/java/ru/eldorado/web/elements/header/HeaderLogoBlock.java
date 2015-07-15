package ru.eldorado.web.elements.header;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;

public class HeaderLogoBlock extends AbstractElement {
    @FindBy(how = How.CSS, css = ".miniCart")
    public MiniCart miniCart;

    @FindBy(how = How.CSS, css = ".headerCart")
    public MiniCart miniCartBitrix;
}