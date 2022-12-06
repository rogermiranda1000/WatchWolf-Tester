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

    public Item(ItemType type, byte amount) throws IllegalArgumentException {
        this.type = type;
        this.setAmount(amount);
    }

    public Item(ItemType type) {
        this(type, (byte) 1);
    }

    public Item(Item i) {
        this(i.type, i.amount);
    }

    public void setAmount(byte amount) throws IllegalArgumentException {
        if (amount < 1) throw new IllegalArgumentException("Quantity must be at least 1");
        this.amount = amount;
    }

    public void setAmount(int amount) throws IllegalArgumentException {
        this.setAmount((byte) amount);
    }

    public void unique() {
        this.setAmount(1);
    }

    public ItemType getType() {
        return this.type;
    }

    public byte getAmount() {
        return this.amount;
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
