package com.rogermiranda1000.watchwolf.entities;

import com.rogermiranda1000.watchwolf.entities.items.Item;

import java.util.List;

public class Inventory {
    private final Item []items;

    public Inventory(Item []items) {
        this.items = Inventory.clone(items);
    }

    public Inventory(List<Item> items) {
        this(items.toArray(new Item[0]));
    }

    public Item []getItems() {
        return Inventory.clone(this.items);
    }

    public int getSize() {
        return this.items.length;
    }

    private static Item []clone(Item []from) {
        Item []r = new Item[from.length];
        for (int n = 0; n < r.length; n++) r[n] = new Item(from[n]);
        return r;
    }
}
