package dev.watchwolf.entities;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Message {
    private static final int MAX_BYTES_SENT = 1024;

    private final DataOutputStream dos;
    private final ArrayList<Byte> send;

    public Message(DataOutputStream dos) {
        this.dos = dos;
        this.send = new ArrayList<>();
    }

    public Message(OutputStream os) {
        this(new DataOutputStream(os));
    }

    public Message(Socket s) throws IOException {
        this(s.getOutputStream());
    }

    public void add(byte b) {
        this.send.add(b);
    }

    public void add(boolean b) {
        if (b) this.add((byte) 0xFF);
        else this.add((byte) 0);
    }

    public void add(double d) {
        SocketHelper.addDouble(this.send, d);
    }

    public void add(float d) {
        SocketHelper.addFloat(this.send, d);
    }

    public void add(short s) {
        SocketHelper.addShort(this.send, s);
    }

    public void add(Object o) {
        if (o instanceof SocketData) {
            ((SocketData)o).sendSocketData(this.send);
            return;
        }

        if (o instanceof Byte) this.add((byte) o);
        else if (o instanceof Boolean) this.add((boolean) o);
        else if (o instanceof Double) this.add((double) o);
        else if (o instanceof Float) this.add((float) o);
        else if (o instanceof String) SocketHelper.addString(this.send, (String)o);
        else if (o instanceof Short) this.add((short) o);
        else throw new IllegalArgumentException("Unkwnown type " + o.getClass());
    }

    public void send() throws IOException {
        List<List<Byte>> chunks = Message.chopped(this.send, Message.MAX_BYTES_SENT);
        for (List<Byte> l : chunks) this.dos.write(SocketHelper.toByteArray(l), 0, l.size());
        // TODO close dos?
    }

    /**
     * Split a list into smaller lists that don't exceed 'L' elements
     * @see <a href="https://stackoverflow.com/a/2895365">https://stackoverflow.com/a/2895365</a>
     * @param list Original list
     * @param L Max number of elements in the sublists
     * @param <T> Type of list
     * @return All the sublists
     */
    static <T> List<List<T>> chopped(List<T> list, final int L) {
        List<List<T>> parts = new ArrayList<List<T>>();
        final int N = list.size();
        for (int i = 0; i < N; i += L) {
            parts.add(new ArrayList<T>(
                    list.subList(i, Math.min(N, i + L)))
            );
        }
        return parts;
    }
}
