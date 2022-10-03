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

    /**
     * @author https://stackoverflow.com/a/13072387/9178470
     */
    public static void addDouble(ArrayList<Byte> out, double d) {
        long lng = Double.doubleToLongBits(d);
        for(int i = 0; i < 8; i++) out.add((byte)((lng >> ((7 - i) * 8)) & 0xff));
    }

    public static void addShort(ArrayList<Byte> out, short s) {
        out.add((byte)(s >> 8));
        out.add((byte)(s & 0xFF));
    }

    public static void fill(ArrayList<Byte> out, int bytes) {
        for (int n = 0; n < bytes; n++) out.add((byte)0);
    }

    public static void discard(DataInputStream dis, int bytes) throws IOException {
        for (int n = 0; n < bytes; n++) dis.readUnsignedByte();
    }

    public static short readShort(DataInputStream dis) throws IOException {
        int msb = dis.readUnsignedByte();
        short lsb = (short)dis.readUnsignedByte();
        return (short)(msb << 8 | lsb);
    }

    public static double readDouble(DataInputStream dis) throws IOException {
        return dis.readDouble();
    }

    public static String readString(DataInputStream dis) throws IOException {
        // TODO check if EOF
        // size
        int size = Short.toUnsignedInt(SocketHelper.readShort(dis));

        // characters
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < size; n++) sb.append((char)dis.read());
        return sb.toString();
    }
}
