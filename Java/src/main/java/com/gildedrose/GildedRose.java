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
        for (int i = 0; i < items.size(); i++) {
            if (ITEM_NAME_SULFURAS.equals(items.get(i).name)) {
                continue;
            }

            updateQualityBeforeSellIn(i);

            items.get(i).sellIn -= 1;

            if (items.get(i).sellIn < 0) {
                updateQualityAfterSellDay(i);
            }
        }
    }

    private void updateQualityBeforeSellIn(int i) {
        if (ITEM_NAME_AGED_BRIE.equals(items.get(i).name) || ITEM_NAME_BACKSTAGE_PASSES.equals(items.get(i).name)) {
            increaseQuality(i);
            return;
        }

        decrementQuality(i);
    }

    private void increaseQuality(int i) {
        incrementQuality(i);

        if (ITEM_NAME_BACKSTAGE_PASSES.equals(items.get(i).name)) {
            if (items.get(i).sellIn < 11) {
                incrementQuality(i);
            }
            if (items.get(i).sellIn < 6) {
                incrementQuality(i);
            }
        }
    }

    private void updateQualityAfterSellDay(int i) {
        if (ITEM_NAME_AGED_BRIE.equals(items.get(i).name)) {
            incrementQuality(i);
            return;
        }

        if (ITEM_NAME_BACKSTAGE_PASSES.equals(items.get(i).name)) {
            items.get(i).quality = 0;
            return;
        }


        decrementQuality(i);
    }

    private void incrementQuality(int i) {
        if (items.get(i).quality < 50) {
            items.get(i).quality += 1;
        }
    }

    private void decrementQuality(int i) {
        if (items.get(i).quality > 0) {
            items.get(i).quality -= 1;
        }
    }

}
