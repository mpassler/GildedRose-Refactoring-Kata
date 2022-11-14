package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void agedBrie() {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertItem(app, "Aged Brie", 9, 11, 0);
    }
    @Test
    void agedBrieIncreasesQualityWithNegativeSellInUntil50() {
        Item[] items = new Item[] { new Item("Aged Brie", -1, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertItem(app, "Aged Brie", -2, 50, 0);
        app.updateQuality();
        assertItem(app, "Aged Brie", -3, 50, 0);
    }

    @Test
    void standardItemIncreasesQualityWithNegativeSellInUntil50() {
        Item[] items = new Item[] { new Item("Standard Item", 1, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertItem(app, "Standard Item", 0, 48, 0);
        app.updateQuality();
        assertItem(app, "Standard Item", -1, 46, 0);
        app.updateQuality();
        assertItem(app, "Standard Item", -2, 44, 0);
        app.updateQuality();
        assertItem(app, "Standard Item", -3, 42, 0);
    }

    @Test
    void standardItemWithQualityZeroAndSellInOne() {
        Item[] items = new Item[] { new Item("Standard Item", 1, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertItem(app, "Standard Item", 0, 1, 0);
        app.updateQuality();
        assertItem(app, "Standard Item", -1, 0, 0);
    }

    @Test
    void standardItemWithQualityZeroAndSellInZero() {
        Item[] items = new Item[] { new Item("Standard Item", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertItem(app, "Standard Item", -1, 0, 0);
        app.updateQuality();
        assertItem(app, "Standard Item", -2, 0, 0);
    }

    @Test
    void AgedBrieWIthHighestQualityDoesNotIncreaseQuality(){
    Item[] items = new Item[] { new Item("Aged Brie", 10, 50) };
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertItem(app, "Aged Brie", 9, 50, 0);
    }

    @Test
    void standardItemDecreasesQuality(){
        Item[] items = new Item[] { new Item("Pineapple Pie", 10, 34) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertItem(app, "Pineapple Pie", 9, 33, 0);
    }

    @Test
    void twoItemsShouldDecreaseQuality(){
        Item[] items = new Item[] {
            new Item("Pineapple Pie", 10, 34),
            new Item("Apple Pie", 10, 43)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertItem(app, "Pineapple Pie", 9, 33, 0);
        assertItem(app, "Apple Pie", 9, 42, 1);
    }

    @Test
    void Sulfuras(){
        Item[] items = new Item[] {
            new Item("Sulfuras, Hand of Ragnaros", 10, 80)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertItem(app, "Sulfuras, Hand of Ragnaros", 10, 80, 0);
        app.updateQuality();
        assertItem(app, "Sulfuras, Hand of Ragnaros", 10, 80, 0);
    }

    @Test
    void BackstagePassesToATAFKAL80ETCConcert() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 11, 30)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertItem(app, "Backstage passes to a TAFKAL80ETC concert", 10, 31, 0);
        app.updateQuality();
        assertItem(app, "Backstage passes to a TAFKAL80ETC concert", 9, 33, 0);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertItem(app, "Backstage passes to a TAFKAL80ETC concert", 5, 41, 0);
        app.updateQuality();
        assertItem(app, "Backstage passes to a TAFKAL80ETC concert", 4, 44, 0);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertItem(app, "Backstage passes to a TAFKAL80ETC concert", 0, 50, 0);
        app.updateQuality();
        assertItem(app, "Backstage passes to a TAFKAL80ETC concert", -1, 0, 0);
    }

    @Test
    void BackstagePassesReachesQuality50() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertItem(app, "Backstage passes to a TAFKAL80ETC concert", 9, 50, 0);
        app.updateQuality();
        assertItem(app, "Backstage passes to a TAFKAL80ETC concert", 8, 50, 0);
    }

    @Test
    void standardItemDecreasesFasterWhenConjured() {
        Item[] items = new Item[] { new Item("Conjured Standard Item", 1, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertItem(app, "Conjured Standard Item", 0, 47, 0);
        app.updateQuality();
        assertItem(app, "Conjured Standard Item", -1, 43, 0);
        app.updateQuality();
        assertItem(app, "Conjured Standard Item", -2, 39, 0);
        app.updateQuality();
        assertItem(app, "Conjured Standard Item", -3, 35, 0);
    }

    private void assertItem(GildedRose app, String name, int sellIn, int quality, int index){
        assertEquals(name, app.items[index].name);
        assertEquals(sellIn, app.items[index].sellIn);
        assertEquals(quality, app.items[index].quality);
    }
}
