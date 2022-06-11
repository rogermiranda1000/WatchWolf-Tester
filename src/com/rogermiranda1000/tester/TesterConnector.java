package com.rogermiranda1000.tester;

import com.rogermiranda1000.entities.*;
import com.rogermiranda1000.serversmanager.ServerErrorNotifier;
import com.rogermiranda1000.serversmanager.ServerManagerPetition;
import com.rogermiranda1000.serversmanager.ServerStartNotifier;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class TesterConnector implements ServerManagerPetition {
    public interface ArrayAdder { public void addToArray(ArrayList<Byte> out, Object []file); }

    private final Socket serversManagerSocket;

    public TesterConnector(Socket serversManagerSocket) {
        this.serversManagerSocket = serversManagerSocket;
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
        out.add((byte)(size&0xFF));
        out.add((byte)((size>>8)&0xFF));

        if (size > 0) arrayAdder.addToArray(out, array);
    }

    private static void addString(ArrayList<Byte> out, String str) {
        Byte []arr = new Byte[str.length()];
        for (int n = 0; n < arr.length; n++) arr[n] = (byte)str.charAt(n);
        TesterConnector.addArray(out, arr, TesterConnector::addRaw);
    }

    /* INTERFACES */
    @Override
    public String startServer(ServerStartNotifier onServerStart, ServerErrorNotifier onError, Map[] maps, Plugin[] plugins, ServerType mcType, String version, ConfigFile[] configFiles) throws IOException {
        ArrayList<Byte> message = new ArrayList<>();

        // start server header
        message.add((byte) 0b000_0_0000);
        message.add((byte) 0b00000001);

        TesterConnector.addArray(message, maps, TesterConnector::addRaw); // TODO
        TesterConnector.addArray(message, plugins, TesterConnector::addRaw); // TODO
        TesterConnector.addString(message, mcType.name());
        TesterConnector.addString(message, version);
        TesterConnector.addArray(message, configFiles, TesterConnector::addRaw); // TODO

        OutputStream out = this.serversManagerSocket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(out);
        dos.write(TesterConnector.toByteArray(message), 0, message.size());
        dos.close();

        return null;
    }
}
