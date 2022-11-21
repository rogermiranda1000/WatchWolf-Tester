package com.rogermiranda1000.watchwolf.entities.items;

import com.rogermiranda1000.watchwolf.entities.SocketData;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;

import java.util.ArrayList;

public class Item extends SocketData {
    static {
        SocketData.setReaderFunction(Item.class, (dis) -> {
            int enumVal = SocketHelper.readShort(dis),
                amount = dis.readUnsignedByte();

            return new Item(ItemType.values()[enumVal], (byte) amount);
        });
    }

    private final ItemType type;
    private byte amount;
    // TODO other attributes

    public Item(ItemType type, byte amount) {
        this.type = type;
        this.amount = amount;
    }

    public void setAmount(byte amount) {
        this.amount = amount;
    }

    public void setAmount(int amount) {
        this.setAmount((byte) amount);
    }

    @Override
    public void sendSocketData(ArrayList<Byte> out) {
        SocketHelper.addShort(out, this.type.ordinal());
        out.add(this.amount);
    }

    @Override
    public String toString() {
        return this.type + "{amount=" + this.amount + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Item)) return false;

        Item that = (Item)obj;
        return this.type.equals(that.type) && this.amount == that.amount;
    }
}
