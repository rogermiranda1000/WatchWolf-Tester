package com.rogermiranda1000.watchwolf.entities;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Message {
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

    public void add(double d) {
        SocketHelper.addDouble(this.send, d);
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
        else if (o instanceof Double) this.add((double) o);
        else if (o instanceof String) SocketHelper.addString(this.send, (String)o);
        else if (o instanceof Short) this.add((short) o);
        else throw new IllegalArgumentException("Unkwnown type " + o.getClass());
    }

    public void send() throws IOException {
        this.dos.write(SocketHelper.toByteArray(this.send), 0, this.send.size());
        // TODO close?
    }
}
