package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
        // Normal degradation of quality after update
    void qualityDegradesPostSellDate() {
        Item[] items = new Item[] { new Item("+5 Dexterity Vest", 1, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.items[0].quality);
    }
    @Test
        // Once the sell by date has passed, Quality degrades twice as fast
    void qualityDegradesTwiceAsFastPostSellDate() {
        Item[] items = new Item[] { new Item("+5 Dexterity Vest", 0, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
//        System.out.println( items[0].sellIn+ " "+ items[0].quality);
        assertEquals(0, app.items[0].quality);
    }
    @Test
        // The Quality of an item is never negative
    void qualityOfItemNotNegative() {
        Item[] items = new Item[] { new Item("+5 Dexterity Vest", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }



    @Test
        // "Aged Brie" actually increases in Quality the older it gets
    void agedBrieQualityIncreasesPostUpdate() {
        Item[] items = new Item[] { new Item("Aged Brie", 1, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.items[0].quality);
    }


    @Test
        // The Quality of an item is never more than 50
    void qualityNeverOver50() {
        Item[] items = new Item[] { new Item("Aged Brie", 1, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
        //  "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
    void sulfurasNeverDecreasesInQuality() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 1, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    /** "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
     * Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
     *
     */
    void backStagePassesIncreaseInQualityBy2WithSellInBetween10to5T1() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 11, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.items[0].quality);
        app.updateQuality();
        assertEquals(3, app.items[0].quality);
    }

    @Test
    /** "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
     * Quality increases by 2 when there are 10 days
     *
     */
    void backStagePassesIncreaseInQualityBy2WithSellInBetween10to5T2() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 6, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].quality);
        app.updateQuality();
        assertEquals(5, app.items[0].quality);
    }


    @Test
    /** "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
     * Quality increases by 3 when there are 5 days
     */
    void backStagePassesIncreaseInQualityBy3WithSellInBelow5() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].quality);
    }

    @Test
    /** "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
     * Quality drops to 0 after the concert
     */
    void backStagePassesQualityDropsTo0AfterConcert() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }



    @Test
        // "Sulfuras" is a legendary item and as such its Quality is 80 and it never alters.
    void SulfurasQualityNeverOver80() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 1, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
    }

    @Test
        //  "Conjured" items degrade in Quality twice as fast as normal items
    void conjuredItemsDegradeTwiceAsFast() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 1, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }
}
