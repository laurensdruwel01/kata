package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }


    private void handleQualityForBackStage(Item item) {
        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.sellIn < 11) {
                changeQuality(item, 1);
            }

            if (item.sellIn < 6) {
                changeQuality(item, 1);
            }
        }
    }

    public void updateQuality() {
        for (Item item : items) {

            if (item.name.equals("Aged Brie") || item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                changeQuality(item, 1);
                handleQualityForBackStage(item);
            } else {
                changeQuality(item, -1);


            }

            // normal degradation
            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.sellIn = item.sellIn - 1;
            }


            if (item.sellIn < 0) { // expiration date
                handleExpiredItem(item);
            }
        }
    }

    private void changeQuality(Item item, int value) {
        int updatedQuality = item.quality + value;
        if (item.quality < 50 && updatedQuality >= 0) {
            item.quality = updatedQuality;
        }
    }
    private void handleExpiredItem(Item item){
            if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                item.quality = 0;
                return;
            }
            if (isDegradable(item)) {
                changeQuality(item, -1);
                return;
            }
            changeQuality(item, 1);

        }


    private boolean isDegradable(Item item) {
        return !item.name.equals("Aged Brie") && !item.name.equals("Backstage passes to a TAFKAL80ETC concert") && !item.name.equals("Sulfuras, Hand of Ragnaros");
    }

}
