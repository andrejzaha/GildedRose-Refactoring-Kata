package com.gildedrose;

import java.util.List;

class GildedRose {
    public static final String ITEM_NAME_SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String ITEM_NAME_BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String ITEM_NAME_AGED_BRIE = "Aged Brie";
    private final List<Item> items;

    public GildedRose(List<Item> items) {
        this.items = List.copyOf(items);
    }

    public List<Item> getItems() {
        return items;
    }

    public void updateQuality() {
        items.forEach(this::processItem);
    }

    private void processItem(Item item) {
        if (ITEM_NAME_SULFURAS.equals(item.name)) {
            return;
        }

        updateQualityBeforeSellIn(item);

        item.sellIn -= 1;

        if (item.sellIn < 0) {
            updateQualityAfterSellDay(item);
        }
    }

    private void updateQualityBeforeSellIn(Item item) {
        if (ITEM_NAME_AGED_BRIE.equals(item.name) || ITEM_NAME_BACKSTAGE_PASSES.equals(item.name)) {
            increaseQuality(item);
            return;
        }

        decrementQuality(item);
    }

    private void increaseQuality(Item item) {
        incrementQuality(item);

        if (ITEM_NAME_BACKSTAGE_PASSES.equals(item.name)) {
            if (item.sellIn < 11) {
                incrementQuality(item);
            }
            if (item.sellIn < 6) {
                incrementQuality(item);
            }
        }
    }

    private void updateQualityAfterSellDay(Item item) {
        if (ITEM_NAME_AGED_BRIE.equals(item.name)) {
            incrementQuality(item);
            return;
        }

        if (ITEM_NAME_BACKSTAGE_PASSES.equals(item.name)) {
            item.quality = 0;
            return;
        }


        decrementQuality(item);
    }

    private void incrementQuality(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
        }
    }

    private void decrementQuality(Item item) {
        if (item.quality > 0) {
            item.quality -= 1;
        }
    }

}
