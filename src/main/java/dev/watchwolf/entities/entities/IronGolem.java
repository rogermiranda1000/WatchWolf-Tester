package dev.watchwolf.entities.entities;

import dev.watchwolf.entities.Position;
import dev.watchwolf.entities.SocketHelper;

import java.util.ArrayList;

/* THIS CLASS WAS AUTOMATICALLY GENERATED; DO NOT MODIFY */
public class IronGolem extends Entity {
    public IronGolem(String UUID, Position position) {
        super(UUID, position);
    }

    public IronGolem(Position position) {
        super(position);
    }

    @Override
    public void sendSocketData(ArrayList<Byte> out) {
        SocketHelper.addShort(out, EntityType.IRON_GOLEM.ordinal());
        this.position.sendSocketData(out);
        SocketHelper.addString(out, this.UUID);
    }
}
