package ru.eldorado.web.elements.header;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.elements.AbstractElement;
import ru.eldorado.web.popups.header.RegionConfirmPopup;

public class HeaderRegion extends AbstractElement {
    @FindBy(how = How.ID, id = "regionConfirmPopup")
    public RegionConfirmPopup regionConfirmPopup;
}
