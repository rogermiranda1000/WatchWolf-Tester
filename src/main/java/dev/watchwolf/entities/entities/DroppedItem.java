package dev.watchwolf.entities.entities;

import dev.watchwolf.entities.Position;
import dev.watchwolf.entities.SocketHelper;
import dev.watchwolf.entities.items.Item;

import java.util.ArrayList;

public class DroppedItem extends Entity {
    private final Item item;

    public DroppedItem(String UUID, Position position, Item item) {
        super(UUID, position);
        this.item = new Item(item);
    }

    public Item getItem() {
        return new Item(this.item);
    }

    @Override
    public void sendSocketData(ArrayList<Byte> out) {
        SocketHelper.addShort(out, EntityType.DROPPED_ITEM.ordinal());
        this.position.sendSocketData(out);
        SocketHelper.addString(out, this.UUID);
        this.item.sendSocketData(out);
    }
}
