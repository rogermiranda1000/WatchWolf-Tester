package com.rogermiranda1000.watchwolf.entities.items;

import com.rogermiranda1000.watchwolf.entities.SocketData;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;

import java.util.ArrayList;
import java.util.UUID;

public class Item extends SocketData {
    static {
        SocketData.setReaderFunction(Item.class, (dis) -> {
            int enumVal = SocketHelper.readShort(dis),
                amount = dis.readUnsignedByte();
            String id = SocketHelper.readString(dis);

            return new Item(ItemType.values()[enumVal], (byte) amount, id.length() == 0 ? null : UUID.fromString(id));
        });
    }

    private ItemType type;
    private byte amount;
    private UUID id;

    public Item(ItemType type, byte amount, UUID id) {

    }

    public Item(ItemType type, byte amount) {
        this(type, amount, null);
    }

    @Override
    public void sendSocketData(ArrayList<Byte> out) {
        SocketHelper.addShort(out, this.type.ordinal());
        out.add(this.amount);
        SocketHelper.addString(out, this.id.toString());
    }

    @Override
    public String toString() {
        return this.type + "{amount=" + this.amount + (id == null ? "" : (",id=" + this.id.toString())) + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Item)) return false;

        Item that = (Item)obj;
        if (this.id == null || that.id == null) return this.type.equals(that.type) && this.amount == that.amount;
        else return this.id.equals(that.id);
    }
}
