package com.rogermiranda1000.watchwolf.entities.entities;

import com.rogermiranda1000.watchwolf.entities.Position;
import com.rogermiranda1000.watchwolf.entities.items.Item;

public class DroppedItem extends Entity {
    private final Item item;

    public DroppedItem(String UUID, Position position, Item item) {
        super(UUID, position);
        this.item = new Item(item);
    }

    public Item getItem() {
        return new Item(this.item);
    }
}
