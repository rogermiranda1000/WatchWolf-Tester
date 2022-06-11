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

public class TesterConnector implements ServerManagerPetition {
    private final Socket serversManagerSocket;

    public TesterConnector(Socket serversManagerSocket) {
        this.serversManagerSocket = serversManagerSocket;
    }

    private static byte []toByteArray(ArrayList<Byte> bytes) {
        byte []r = new byte[bytes.size()];
        for (int x = 0; x < r.length; x++) r[x] = bytes.get(x);
        return r;
    }

    @Override
    public String startServer(ServerStartNotifier onServerStart, ServerErrorNotifier onError, Map[] maps, Plugin[] plugins, ServerType mcType, String version, ConfigFile[] configFiles) throws IOException {
        ArrayList<Byte> message = new ArrayList<>();
        message.add((byte) 0b000_0_000000000001); // start server header
        OutputStream out = this.serversManagerSocket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(out);
        dos.write(TesterConnector.toByteArray(message), 0, message.size());

        dos.flush();
        dos.close();

        return null;
    }
}
