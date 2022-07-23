package com.gildedrose;

import java.util.List;

public class ProcessPermissionChecker {

    public static final String ITEM_NAME_SULFURAS = "Sulfuras, Hand of Ragnaros";
    private final List<String> itemNamesNotProcessed = List.of(ITEM_NAME_SULFURAS);

    boolean shouldProcessItem(Item item) {
        return !itemNamesNotProcessed.contains(item.name);
    }
}
