package dev.watchwolf.entities;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SocketHelper {
    public static byte []toByteArray(List<Byte> bytes) {
        byte []r = new byte[bytes.size()];
        for (int x = 0; x < r.length; x++) r[x] = bytes.get(x);
        return r;
    }

    /**
     * Add a byte[] directly to the socket
     * @param out Where to add
     * @param file Array to add
     */
    public static void addRaw(ArrayList<Byte> out, Object []file) {
        for (Byte b : (Byte[])file) out.add((byte)b);
    }

    public static <T> void addArray(ArrayList<Byte> out, T[] array, ArrayAdder<T> arrayAdder) {
        SocketHelper.addShort(out, (short)array.length);
        if (array.length > 0) arrayAdder.addToArray(out, array);
    }

    public static <T extends SocketData> void addArray(ArrayList<Byte> out, T[] array) {
        SocketHelper.addShort(out, (short)array.length);
        for (T e : array) e.sendSocketData(out);
    }

    public static void addString(ArrayList<Byte> out, String str) {
        Byte []arr = new Byte[str.length()];
        for (int n = 0; n < arr.length; n++) arr[n] = (byte)str.charAt(n);
        SocketHelper.addArray(out, arr, SocketHelper::addRaw);
    }

    /**
     * Adds a double to send via sockets.
     * @author https://stackoverflow.com/a/13072387/9178470
     * @param out Where to add
     * @param d Double
     */
    public static void addDouble(ArrayList<Byte> out, double d) {
        long lng = Double.doubleToLongBits(d);
        for(int i = 0; i < 8; i++) out.add((byte)((lng >> ((7 - i) * 8)) & 0xff));
    }

    public static void addFloat(ArrayList<Byte> out, float d) {
        SocketHelper.addDouble(out, (double) d);
    }

    public static void addBool(ArrayList<Byte> out, boolean b) {
        if (b) out.add((byte) 0xFF);
        else out.add((byte) 0);
    }

    public static boolean readBool(DataInputStream dis) throws IOException {
        return dis.readUnsignedByte() != 0x00;
    }

    public static void addShort(ArrayList<Byte> out, int s) {
        out.add((byte)(s & 0xFF));
        out.add((byte)((s >> 8)&0xFF));
    }

    public static void fill(ArrayList<Byte> out, int bytes) {
        for (int n = 0; n < bytes; n++) out.add((byte)0);
    }

    public static void discard(DataInputStream dis, int bytes) throws IOException {
        for (int n = 0; n < bytes; n++) dis.readUnsignedByte();
    }

    public static int readShort(DataInputStream dis) throws IOException {
        int lsb = dis.readUnsignedByte();
        short msb = (short)dis.readUnsignedByte();
        return (msb << 8 | lsb);
    }

    public static double readDouble(DataInputStream dis) throws IOException {
        long lng = 0;
        for (int i = 0; i < 8; i++) lng = (lng << 8) | dis.readUnsignedByte();
        return Double.longBitsToDouble(lng);
    }

    public static float readFloat(DataInputStream dis) throws IOException {
        return (float) SocketHelper.readDouble(dis);
    }

    public static String readString(DataInputStream dis) throws IOException {
        // TODO check if EOF
        // size
        int size = SocketHelper.readShort(dis);

        // characters
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < size; n++) sb.append((char)dis.read());
        return sb.toString();
    }
}
