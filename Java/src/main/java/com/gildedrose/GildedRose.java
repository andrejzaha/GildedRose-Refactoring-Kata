package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            updateQualityBeforeSellIn(i);

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn -= 1;
            }

            if (items[i].sellIn < 0) {
                updateQualityAfterSellDay(i);
            }
        }
    }

    private void updateQualityBeforeSellIn(int i) {
        if (items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
            return;
        }

        if (items[i].name.equals("Aged Brie") || items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            increaseQuality(i);
            return;
        }

        if (items[i].quality > 0) {
            items[i].quality -= 1;
        }
    }

    private void increaseQuality(int i) {
        tryToIncrementQuality(i);

        if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (items[i].sellIn < 11) {
                tryToIncrementQuality(i);
            }
            if (items[i].sellIn < 6) {
                tryToIncrementQuality(i);
            }
        }
    }

    private void updateQualityAfterSellDay(int i) {
        if (items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
            return;
        }

        if (items[i].name.equals("Aged Brie")) {
            tryToIncrementQuality(i);
            return;
        }

        if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
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
