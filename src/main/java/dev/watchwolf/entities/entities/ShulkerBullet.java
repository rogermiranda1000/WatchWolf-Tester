package dev.watchwolf.entities.entities;

import dev.watchwolf.entities.Position;
import dev.watchwolf.entities.SocketHelper;

import java.util.ArrayList;

/* THIS CLASS WAS AUTOMATICALLY GENERATED; DO NOT MODIFY */
public class ShulkerBullet extends Entity {
    public ShulkerBullet(String UUID, Position position) {
        super(UUID, position);
    }

    @Override
    public void sendSocketData(ArrayList<Byte> out) {
        SocketHelper.addShort(out, EntityType.DroppedItem.ordinal());
        this.position.sendSocketData(out);
        SocketHelper.addString(out, this.UUID);
    }
}
