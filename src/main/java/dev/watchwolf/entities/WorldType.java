package dev.watchwolf.entities;

import java.util.ArrayList;

public enum WorldType {
    DEFAULT(0),
    FLAT(1);

    private byte send;
    private WorldType(int send) {
        this.send = (byte) send;
    }

    public void sendSocketData(ArrayList<Byte> out) {
        out.add(this.send);
    }
}
