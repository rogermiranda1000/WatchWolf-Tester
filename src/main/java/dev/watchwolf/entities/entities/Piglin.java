package dev.watchwolf.entities.entities;

import dev.watchwolf.entities.Position;
import dev.watchwolf.entities.SocketHelper;

import java.util.ArrayList;

/* THIS CLASS WAS AUTOMATICALLY GENERATED; DO NOT MODIFY */
public class Piglin extends Entity {
    public Piglin(String UUID, Position position) {
        super(UUID, position);
    }

    public Piglin(Position position) {
        super(position);
    }

    @Override
    public void sendSocketData(ArrayList<Byte> out) {
        SocketHelper.addShort(out, EntityType.PIGLIN.ordinal());
        this.position.sendSocketData(out);
        SocketHelper.addString(out, this.UUID);
    }
}
