package dev.watchwolf.entities;

import dev.watchwolf.entities.entities.Entity;
import dev.watchwolf.entities.entities.EntityType;

import java.util.ArrayList;

public enum WorldType {
    DEFAULT(0),
    FLAT(1);

    private byte send;
    private WorldType(int send) {
        this.send = (byte) send;
    }

    static {
        SocketData.setReaderFunction(Entity.class, (dis) -> {
            return null; // TODO
        });
    }

    public void sendSocketData(ArrayList<Byte> out) {
        out.add(this.send);
    }
}
