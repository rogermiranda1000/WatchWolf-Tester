package com.rogermiranda1000.watchwolf.entities;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class SocketHelper {
    public static byte []toByteArray(ArrayList<Byte> bytes) {
        byte []r = new byte[bytes.size()];
        for (int x = 0; x < r.length; x++) r[x] = bytes.get(x);
        return r;
    }

    /**
     * Add a byte[] directly to the socket
     */
    public static void addRaw(ArrayList<Byte> out, Object []file) {
        for (Byte b : (Byte[])file) out.add((byte)b);
    }

    public static void addArray(ArrayList<Byte> out, Object[] array, ArrayAdder arrayAdder) {
        int size = array.length;
        out.add((byte)((size>>8)&0xFF)); // MSB
        out.add((byte)(size&0xFF));      // LSB

        if (size > 0) arrayAdder.addToArray(out, array);
    }

    public static void addString(ArrayList<Byte> out, String str) {
        Byte []arr = new Byte[str.length()];
        for (int n = 0; n < arr.length; n++) arr[n] = (byte)str.charAt(n);
        SocketHelper.addArray(out, arr, SocketHelper::addRaw);
    }

    public static short readShort(DataInputStream dis) throws IOException {
        short msb = (short)((short)dis.readByte() << 8);
        return (short)(msb | dis.readByte()); // LSB
    }

    public static String readString(DataInputStream dis) throws IOException {
        // TODO check if EOF
        // size
        short size = SocketHelper.readShort(dis);

        // characters
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < size; n++) sb.append((char)dis.read());
        return sb.toString();
    }
}
