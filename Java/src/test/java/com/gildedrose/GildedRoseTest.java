package com.gildedrose;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        GildedRose app = create(Collections.singletonList(new Item("foo", 0, 0)));
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void decrementQualityForRegularItemsAndZeroSellIn() {
        String itemName = "other-non-specific";

        GildedRose app = create(Collections.singletonList(new Item(itemName, 0, 3)));
        assertEquals(itemName, app.items[0].name);
        assertEquals(0, app.items[0].sellIn);
        assertEquals(3, app.items[0].quality);

        app.updateQuality();
        assertEquals(itemName, app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);

        app.updateQuality();
        assertEquals(itemName, app.items[0].name);
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);


        app.updateQuality();
        assertEquals(itemName, app.items[0].name);
        assertEquals(-3, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void decrementQualityForRegularItemsAndPositiveSellIn() {
        String itemName = "other-non-specific";

        GildedRose app = create(Collections.singletonList(new Item(itemName, 3, 2)));
        assertEquals(itemName, app.items[0].name);
        assertEquals(3, app.items[0].sellIn);
        assertEquals(2, app.items[0].quality);

        app.updateQuality();
        assertEquals(itemName, app.items[0].name);
        assertEquals(2, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
    }

    @Test
    void agedBrieWithQualityOverThresholdAndPositiveSellIn() {
        String agedBrie = "Aged Brie";
        GildedRose app = create(Collections.singletonList(new Item(agedBrie, 1, 50)));

        assertEquals(agedBrie, app.items[0].name);
        assertEquals(1, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);

        app.updateQuality();
        assertEquals(agedBrie, app.items[0].name);
        assertEquals(0, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void agedBrieWithQualityOverThresholdAndZeroSellIn() {
        String agedBrie = "Aged Brie";
        GildedRose app = create(Collections.singletonList(new Item(agedBrie, 0, 50)));

        assertEquals(agedBrie, app.items[0].name);
        assertEquals(0, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);

        app.updateQuality();
        assertEquals(agedBrie, app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void agedBrieWithQualityBellowThresholdAndZeroSellInIncrementQualityAtTheBeginning() {
        String agedBrie = "Aged Brie";
        GildedRose app = create(Collections.singletonList(new Item(agedBrie, 0, 49)));

        assertEquals(agedBrie, app.items[0].name);
        assertEquals(0, app.items[0].sellIn);
        assertEquals(49, app.items[0].quality);

        app.updateQuality();
        assertEquals(agedBrie, app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void agedBrieWithQualityBellowThresholdAndZeroSellInIncrementQualityTwice() {
        String agedBrie = "Aged Brie";
        GildedRose app = create(Collections.singletonList(new Item(agedBrie, 0, 48)));

        assertEquals(agedBrie, app.items[0].name);
        assertEquals(0, app.items[0].sellIn);
        assertEquals(48, app.items[0].quality);

        app.updateQuality();
        assertEquals(agedBrie, app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void backstagePositiveSellInIncrementQualityBecauseSellInLessThanThresholdAndQualityBellowThreshold() {
        String backstage = "Backstage passes to a TAFKAL80ETC concert";
        GildedRose app = create(Collections.singletonList(new Item(backstage, 10, 49)));

        assertEquals(backstage, app.items[0].name);
        assertEquals(10, app.items[0].sellIn);
        assertEquals(49, app.items[0].quality);

        app.updateQuality();
        assertEquals(backstage, app.items[0].name);
        assertEquals(9, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void backstagePositiveSellInIncrementTwiceQualityBecauseSellInLessThanThresholdAndQualityBellowThreshold() {
        String backstage = "Backstage passes to a TAFKAL80ETC concert";
        GildedRose app = create(Collections.singletonList(new Item(backstage, 10, 48)));

        assertEquals(backstage, app.items[0].name);
        assertEquals(10, app.items[0].sellIn);
        assertEquals(48, app.items[0].quality);

        app.updateQuality();
        assertEquals(backstage, app.items[0].name);
        assertEquals(9, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void backstagePositiveSellInIncrementX3QualityBecauseSellInLessThanThresholdAndQualityBellowThreshold() {
        String backstage = "Backstage passes to a TAFKAL80ETC concert";
        GildedRose app = create(Collections.singletonList(new Item(backstage, 5, 47)));

        assertEquals(backstage, app.items[0].name);
        assertEquals(5, app.items[0].sellIn);
        assertEquals(47, app.items[0].quality);

        app.updateQuality();
        assertEquals(backstage, app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void backstageZeroSellIn() {
        String backstage = "Backstage passes to a TAFKAL80ETC concert";
        GildedRose app = create(Collections.singletonList(new Item(backstage, 0, 50)));

        assertEquals(backstage, app.items[0].name);
        assertEquals(0, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);

        app.updateQuality();
        assertEquals(backstage, app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void ragnarosWithNegativeInitialSellInMaintainQuality() {
        String ragnaros = "Sulfuras, Hand of Ragnaros";
        GildedRose app = create(Collections.singletonList(new Item(ragnaros, -1, 50)));

        assertEquals(ragnaros, app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);

        app.updateQuality();
        assertEquals(ragnaros, app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void ragnarosWithNegativeInitialSellInAndNegativeInitialQualityMaintainQuality() {
        String ragnaros = "Sulfuras, Hand of Ragnaros";
        GildedRose app = create(Collections.singletonList(new Item(ragnaros, -1, -1)));

        assertEquals(ragnaros, app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(-1, app.items[0].quality);

        app.updateQuality();
        assertEquals(ragnaros, app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(-1, app.items[0].quality);
    }

    private GildedRose create(List<Item> items) {
        return new GildedRose(items.toArray(new Item[0]));
    }
}
