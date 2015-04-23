package ru.eldorado.web.elements.catalog;

import ru.eldorado.web.elements.AbstractElement;

public class CatalogFilter extends AbstractElement {
    public void applyFilter(String name) {
        getSelf().findElementByLinkText(name).click();
    }
}
