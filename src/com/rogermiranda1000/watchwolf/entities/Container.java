package com.rogermiranda1000.watchwolf.entities;

import com.rogermiranda1000.watchwolf.entities.items.Item;
import com.rogermiranda1000.watchwolf.entities.items.ItemType;

import java.util.ArrayList;
import java.util.List;

public class Container extends SocketData {
    private final Item []items;

    public Container(Item []items) {
        this.items = Container.clone(items);
    }

    public Container(List<Item> items) {
        this(items.toArray(new Item[0]));
    }

    public Item []getItems() {
        return Container.clone(this.items);
    }

    public int getSize() {
        return this.items.length;
    }

    private static Item []clone(Item []from) {
        Item []r = new Item[from.length];
        for (int n = 0; n < r.length; n++) {
            if (from[n] == null) continue;
            r[n] = new Item(from[n]);
        }
        return r;
    }

    static {
        SocketData.setReaderFunction(Container.class, (dis) -> {
            Item air = new Item(ItemType.AIR);
            int size = SocketHelper.readShort(dis);
            Item []get = new Item[size];
            for (int n = 0; n < get.length; n++) {
                get[n] = (Item)SocketData.readSocketData(dis, Item.class);
                if (get[n].equals(air)) get[n] = null;
            }

            return new Container(get);
        });
    }

    @Override
    public void sendSocketData(ArrayList<Byte> out) {
        SocketHelper.addArray(out, this.items, (o,obj) -> {
            Item air = new Item(ItemType.AIR);
            for (int n = 0; n < obj.length; n++) {
                Item send = obj[n];
                if (send == null) send = air;
                send.sendSocketData(o);
            }
        });
    }
}
