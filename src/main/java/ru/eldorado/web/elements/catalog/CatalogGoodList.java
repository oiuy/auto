package ru.eldorado.web.elements.catalog;

import com.codeborne.selenide.ElementsCollection;
import ru.eldorado.web.elements.AbstractElement;

public class CatalogGoodList extends AbstractElement {
    private static final String GOODS_CSS = ".goodsList div.item";

    public CatalogGoodItem addFirstToCart() {
        ElementsCollection items = getSelf().findAll(GOODS_CSS);
        if (items.isEmpty()) {
            throw new AssertionError("Catalog is empty");
        }

        CatalogGoodItem item = initContainer(items.get(0), CatalogGoodItem.class);
        item.addToCart();

        return item;
    }
}
