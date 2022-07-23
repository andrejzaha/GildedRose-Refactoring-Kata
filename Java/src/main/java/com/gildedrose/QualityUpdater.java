package com.gildedrose;

import java.util.List;

public class QualityUpdater {
    private static final String ITEM_NAME_BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String ITEM_NAME_AGED_BRIE = "Aged Brie";

    private final List<String> itemNamesThatCanIncreaseQuality = List.of(
        ITEM_NAME_AGED_BRIE,
        ITEM_NAME_BACKSTAGE_PASSES
    );

    void updateBeforeExpiration(Item item) {
        if (itemNamesThatCanIncreaseQuality.contains(item.name)) {
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

    void updateAfterExpiration(Item item) {
        if (!isItemExpired(item)) {
            return;
        }
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

    private boolean isItemExpired(Item item) {
        return item.sellIn < 0;
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
