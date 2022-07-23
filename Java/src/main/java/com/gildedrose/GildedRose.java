package com.gildedrose;

class GildedRose {
    public static final String ITEM_NAME_SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String ITEM_NAME_BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String ITEM_NAME_AGED_BRIE = "Aged Brie";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            updateQualityBeforeSellIn(i);

            if (!ITEM_NAME_SULFURAS.equals(items[i].name)) {
                items[i].sellIn -= 1;
            }

            if (items[i].sellIn < 0) {
                updateQualityAfterSellDay(i);
            }
        }
    }

    private void updateQualityBeforeSellIn(int i) {
        if (ITEM_NAME_SULFURAS.equals(items[i].name)) {
            return;
        }

        if (ITEM_NAME_AGED_BRIE.equals(items[i].name) || ITEM_NAME_BACKSTAGE_PASSES.equals(items[i].name)) {
            increaseQuality(i);
            return;
        }

        if (items[i].quality > 0) {
            items[i].quality -= 1;
        }
    }

    private void increaseQuality(int i) {
        tryToIncrementQuality(i);

        if (ITEM_NAME_BACKSTAGE_PASSES.equals(items[i].name)) {
            if (items[i].sellIn < 11) {
                tryToIncrementQuality(i);
            }
            if (items[i].sellIn < 6) {
                tryToIncrementQuality(i);
            }
        }
    }

    private void updateQualityAfterSellDay(int i) {
        if (ITEM_NAME_SULFURAS.equals(items[i].name)) {
            return;
        }

        if (ITEM_NAME_AGED_BRIE.equals(items[i].name)) {
            tryToIncrementQuality(i);
            return;
        }

        if (ITEM_NAME_BACKSTAGE_PASSES.equals(items[i].name)) {
            items[i].quality = 0;
            return;
        }


        if (items[i].quality > 0) {
            items[i].quality -= 1;
        }
    }

    private void tryToIncrementQuality(int i) {
        if (items[i].quality < 50) {
            items[i].quality += 1;
        }
    }

}
