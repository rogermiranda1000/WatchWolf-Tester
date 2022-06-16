package com.rogermiranda1000.watchwolf.tester;

import com.rogermiranda1000.watchwolf.entities.*;
import com.rogermiranda1000.watchwolf.server.ServerPetition;
import com.rogermiranda1000.watchwolf.server.ServerStopNotifier;
import com.rogermiranda1000.watchwolf.serversmanager.*;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class TesterConnector implements ServerManagerPetition, ServerPetition, Runnable {
    public interface ArrayAdder { public void addToArray(ArrayList<Byte> out, Object []file); }

    private final Socket serversManagerSocket;
    private ServerStartNotifier onServerStart;
    private Socket serverManagerSocket;

    public TesterConnector(Socket serversManagerSocket) {
        this.serversManagerSocket = serversManagerSocket;
    }

    public void setServerManagerSocket(Socket s) {
        this.serverManagerSocket = s;
    }

    public void close() {
        try {
            this.serversManagerSocket.close();
            if (this.serverManagerSocket != null) this.serverManagerSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read async responses
     */
    @Override
    public void run() {
        while(!this.serversManagerSocket.isClosed()) {
            synchronized (this) {
                try {
                    DataInputStream dis = new DataInputStream(this.serversManagerSocket.getInputStream());
                    this.processAsyncReturn(dis.readShort());
                } catch (EOFException ignore) {
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void processAsyncReturn(short header) throws IOException {
        switch (header) {
            case 0b000_1_000000000010: // server started
                if (this.onServerStart != null) this.onServerStart.onServerStart();
                else System.out.println("Server started, but notifier not setted");
                break;

            default:
                System.out.println("Uknown request: " + header);
        }
    }

    /* HELPER FUNCTIONS */
    private static byte []toByteArray(ArrayList<Byte> bytes) {
        byte []r = new byte[bytes.size()];
        for (int x = 0; x < r.length; x++) r[x] = bytes.get(x);
        return r;
    }

    /**
     * Add a byte[] directly to the socket
     */
    private static void addRaw(ArrayList<Byte> out, Object []file) {
        for (Byte b : (Byte[])file) out.add((byte)b);
    }

    private static void addArray(ArrayList<Byte> out, Object[] array, ArrayAdder arrayAdder) {
        int size = array.length;
        out.add((byte)((size>>8)&0xFF)); // MSB
        out.add((byte)(size&0xFF));      // LSB

        if (size > 0) arrayAdder.addToArray(out, array);
    }

    private static void addString(ArrayList<Byte> out, String str) {
        Byte []arr = new Byte[str.length()];
        for (int n = 0; n < arr.length; n++) arr[n] = (byte)str.charAt(n);
        TesterConnector.addArray(out, arr, TesterConnector::addRaw);
    }

    private static String readString(DataInputStream dis) throws IOException {
        // TODO check if EOF
        // size
        short size = (short) (dis.read() << 8); // MSB
        size |= (short) dis.read(); // LSB

        // characters
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < size; n++) sb.append((char)dis.read());
        return sb.toString();
    }

    /* INTERFACES */
    @Override
    public String startServer(ServerStartNotifier onServerStart, ServerErrorNotifier onError, Map[] maps, Plugin[] plugins, ServerType mcType, String version, ConfigFile[] configFiles) throws IOException {
        this.onServerStart = onServerStart;

        ArrayList<Byte> message = new ArrayList<>();

        // start server header
        message.add((byte) 0b000_0_0000);
        message.add((byte) 0b00000001);

        TesterConnector.addArray(message, maps, TesterConnector::addRaw); // TODO
        TesterConnector.addArray(message, plugins, TesterConnector::addRaw); // TODO
        TesterConnector.addString(message, mcType.name());
        TesterConnector.addString(message, version);
        TesterConnector.addArray(message, configFiles, TesterConnector::addRaw); // TODO

        DataOutputStream dos = new DataOutputStream(this.serversManagerSocket.getOutputStream());
        synchronized (this) { // response with return -> reserve the socket before the thread does
            dos.write(TesterConnector.toByteArray(message), 0, message.size());

            // read response
            DataInputStream dis = new DataInputStream(this.serversManagerSocket.getInputStream());
            short r = dis.readShort();
            while (r != 4097) {
                this.processAsyncReturn(r); // expected return, found async return from another request
                r = dis.readShort();
            }
            return TesterConnector.readString(dis);
        }
    }

    @Override
    public void opPlayer(String nick) throws IOException {
        ArrayList<Byte> message = new ArrayList<>();

        // op player header
        message.add((byte) 0b001_0_0000);
        message.add((byte) 0b00000001);
        message.add((byte) 0x00);
        message.add((byte) 0x04);

        TesterConnector.addString(message, nick);

        DataOutputStream dos = new DataOutputStream(this.serversManagerSocket.getOutputStream());
        dos.write(TesterConnector.toByteArray(message), 0, message.size());
    }

    @Override
    public void whitelistPlayer(String nick) throws IOException {
        // TODO
    }

    @Override
    public void stopServer(ServerStopNotifier onServerStop) throws IOException {
        ArrayList<Byte> message = new ArrayList<>();

        // stop server header
        message.add((byte) 0b001_0_0000);
        message.add((byte) 0b00000001);
        message.add((byte) 0x00);
        message.add((byte) 0x01);

        DataOutputStream dos = new DataOutputStream(this.serversManagerSocket.getOutputStream());
        dos.write(TesterConnector.toByteArray(message), 0, message.size());

        // TODO onServerStop?
    }
}
