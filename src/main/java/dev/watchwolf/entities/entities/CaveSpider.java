package dev.watchwolf.entities.entities;

import dev.watchwolf.entities.Position;
import dev.watchwolf.entities.SocketHelper;

import java.util.ArrayList;

/* THIS CLASS WAS AUTOMATICALLY GENERATED; DO NOT MODIFY */
public class CaveSpider extends Entity {
    public CaveSpider(String UUID, Position position) {
        super(UUID, position);
    }

    public CaveSpider(Position position) {
        super(position);
    }

    @Override
    public void sendSocketData(ArrayList<Byte> out) {
        SocketHelper.addShort(out, EntityType.CAVE_SPIDER.ordinal());
        this.position.sendSocketData(out);
        SocketHelper.addString(out, this.UUID);
    }
}
