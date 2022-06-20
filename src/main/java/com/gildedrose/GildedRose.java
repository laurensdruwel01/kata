package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {


            if (!item.name.equals("Aged Brie") && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (item.quality > 0) {
                    if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                        changeQuality(item, -1);
                    }
                }
            } else {

                changeQuality(item, 1);

                if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.sellIn < 11) {
                        changeQuality(item, 1);
                    }

                    if (item.sellIn < 6) {
                        changeQuality(item, 1);
                    }
                }
            }

            // normal degradation
            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.sellIn = item.sellIn - 1;
            }


            if (item.sellIn < 0) { // quality degrades twice as fast post sell date
                if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    item.quality = 0;
                    return;
                }


                if (isDegradable(item)) {
                    if (item.quality > 0) {
                        changeQuality(item, -1);
                    }
                } else {
                    changeQuality(item, 1);
                }
            }
        }
    }

    private void changeQuality(Item item, int value) {
        if (item.quality < 50) {
            item.quality = item.quality + value;
        }
    }

    private boolean isDegradable(Item item) {
        return !item.name.equals("Aged Brie") && !item.name.equals("Backstage passes to a TAFKAL80ETC concert") && !item.name.equals("Sulfuras, Hand of Ragnaros");
    }

}
