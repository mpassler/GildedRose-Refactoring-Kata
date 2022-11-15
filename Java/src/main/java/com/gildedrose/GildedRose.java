package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            switch (item.name) {
                case "Aged Brie":
                    caseAgedBrie(item);
                    break;
                case "Backstage passes to a TAFKAL80ETC concert":
                    caseBackstagePasses(item);
                    break;
                case "Sulfuras, Hand of Ragnaros":
                    break;
                default:
                    caseDefault(item);
                    break;
            }
        }
    }

    private static void caseDefault(Item item) {
        decreaseQuality(item);
        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            decreaseQuality(item);
        }
    }

    private static void caseBackstagePasses(Item item) {
        if (item.sellIn < 6) {
            increaseQuality(item);
            increaseQuality(item);
            increaseQuality(item);
        } else if (item.sellIn < 11) {
            increaseQuality(item);
            increaseQuality(item);
        } else {
            increaseQuality(item);
        }
        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }

    private static void caseAgedBrie(Item item) {
        increaseQuality(item);
        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            increaseQuality(item);
        }
    }

    private static void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private static void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }
}
