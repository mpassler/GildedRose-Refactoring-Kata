package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    String backstage = "Backstage passes to a TAFKAL80ETC concert";
    String sulfuras = "Sulfuras, Hand of Ragnaros";
    String brie = "Aged Brie";

    int decreaseFactor = 1;

    Item actualItem;

    public void updateQuality() {
        for (Item item : items) {
            actualItem = item;
            handleBackstage();
            handleBrie();
            handleStandardItem();
        }
    }

    private void handleStandardItem() {
        if (!isSpecialItem()) {
            actualItem.sellIn = decreaseSellInByOne();
            decreaseQualityGreaterThanZero();
            if (actualItem.quality <= 0){
                actualItem.quality = increaseQualityByOne();
            }

            if (actualItem.sellIn < 0) {
                decreaseQualityGreaterThanZero();
            }
        }

    }

    private void decreaseQualityGreaterThanZero() {
        if (actualItem.quality > 0) {
            actualItem.quality = decreaseQualityByFactor();
        }
    }

    private boolean isConjured() {
        return actualItem.name.contains("Conjured");
    }
    private void handleBrie() {
        if (actualItem.name.equals(brie)){
            actualItem.sellIn = decreaseSellInByOne();
            increaseQualityBelowFifty();
            if (actualItem.sellIn < 0) {
                increaseQualityBelowFifty();
            }
        }

    }

    private void increaseQualityBelowFifty() {
        if (isQualityBelow50()) {
            actualItem.quality = increaseQualityByOne();
        }
    }

    private void handleBackstage() {

        if (actualItem.name.equals(backstage))
        {
            increaseQualityBelowFifty();
                if (actualItem.sellIn < 11) {
                    increaseQualityBelowFifty();
                }
                if (actualItem.sellIn < 6) {
                    increaseQualityBelowFifty();
                }
            actualItem.sellIn = decreaseSellInByOne();
            if (actualItem.sellIn < 0) {
                actualItem.quality = 0;
            }
        }
    }

    private boolean isQualityBelow50() {
        return actualItem.quality < 50;
    }

    private boolean isSpecialItem() {
        return (actualItem.name.equals(brie)
            || actualItem.name.equals(backstage)
            || actualItem.name.equals(sulfuras));
    }

    private int decreaseQualityByFactor() {
        if(isConjured()) {
            decreaseFactor = 2;
        }
        return actualItem.quality - decreaseFactor;
    }

    private int decreaseSellInByOne() {
        return actualItem.sellIn - 1;
    }

    private int increaseQualityByOne() {
        return actualItem.quality + 1;
    }
}
