package dev.watchwolf.entities;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

public enum Difficulty {
    PEACEFUL,
    EASY,
    NORMAL,
    HARD;

    public static Difficulty fromSocketData(DataInputStream dis) throws IOException {
        int index = dis.readUnsignedByte();
        return Difficulty.values()[index];
    }

    public void sendSocketData(ArrayList<Byte> out) {
        out.add((byte)this.ordinal());
    }

    public void sendSocketData(Message msg) {
        msg.add((byte)this.ordinal());
    }
}
