package com.gildedrose;

import java.util.List;

class GildedRose {

    private final List<Item> items;
    private final ProcessPermissionChecker processPermissionChecker = new ProcessPermissionChecker();
    private final QualityUpdater qualityUpdater = new QualityUpdater();

    public GildedRose(List<Item> items) {
        this.items = List.copyOf(items);
    }

    public List<Item> getItems() {
        return List.copyOf(items);
    }

    public void updateQuality() {
        items.stream()
            .filter(processPermissionChecker::shouldProcessItem)
            .forEach(this::processItem);
    }

    private void processItem(Item item) {
        qualityUpdater.updateBeforeExpiration(item);
        item.sellIn -= 1;
        qualityUpdater.updateAfterExpiration(item);
    }

}
