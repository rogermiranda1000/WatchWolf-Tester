package com.rogermiranda1000.watchwolf.tester;

import com.rogermiranda1000.watchwolf.entities.*;
import com.rogermiranda1000.watchwolf.server.ServerPetition;
import com.rogermiranda1000.watchwolf.server.ServerStopNotifier;
import com.rogermiranda1000.watchwolf.serversmanager.*;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class TesterConnector implements ServerManagerPetition, ServerPetition, Runnable {
    private final Socket serversManagerSocket;
    private ServerStartNotifier onServerStart;
    private ServerErrorNotifier onServerError;
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
            synchronized (this.serversManagerSocket) {
                try {
                    DataInputStream dis = new DataInputStream(this.serversManagerSocket.getInputStream());
                    this.processAsyncReturn(dis.readShort(), dis);
                } catch (EOFException | SocketException ignore) {
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void processAsyncReturn(short header, DataInputStream dis) throws IOException {
        switch (header) {
            case 0b000_1_000000000010: // server started
                if (this.onServerStart != null) this.onServerStart.onServerStart();
                else System.out.println("Server started, but notifier not setted");
                break;

            case 0b000_1_000000000011: // error
                String error = SocketHelper.readString(dis); // even if no error notifier, we need to remove the string from the socket
                if (this.onServerError != null) this.onServerError.onError(error);
                break;

            default:
                System.out.println("Uknown request: " + header);
        }
    }

    /* INTERFACES */
    @Override
    public String startServer(ServerStartNotifier onServerStart, ServerErrorNotifier onError, ServerType mcType, String version, Plugin[] plugins, Map[] maps, ConfigFile[] configFiles) throws IOException {
        this.onServerStart = onServerStart;
        this.onServerError = onError;

        ArrayList<Byte> message = new ArrayList<>();

        // start server header
        message.add((byte) 0b000_0_0000);
        message.add((byte) 0b00000001);

        SocketHelper.addString(message, mcType.name());
        SocketHelper.addString(message, version);

        SocketHelper.addArray(message, plugins, (ArrayList<Byte> out, Object []file) -> {
            // add the plugins
            for (Plugin p : (Plugin[]) file) {
                p.sendSocketData(out);
            }
        });

        SocketHelper.addArray(message, maps, SocketHelper::addRaw); // TODO
        SocketHelper.addArray(message, configFiles, SocketHelper::addRaw); // TODO

        DataOutputStream dos = new DataOutputStream(this.serversManagerSocket.getOutputStream());
        synchronized (this.serversManagerSocket) { // response with return -> reserve the socket before the thread does
            dos.write(SocketHelper.toByteArray(message), 0, message.size());

            // read response
            DataInputStream dis = new DataInputStream(this.serversManagerSocket.getInputStream());
            short r = SocketHelper.readShort(dis);
            while (r != 4097) {
                this.processAsyncReturn(r, dis); // expected return, found async return from another request
                r = SocketHelper.readShort(dis);
            }
            return SocketHelper.readString(dis);
        }
    }

    @Override
    public void opPlayer(String nick) throws IOException {
        if (this.serverManagerSocket == null) return;
        ArrayList<Byte> message = new ArrayList<>();

        // op player header
        message.add((byte) 0b001_0_0000);
        message.add((byte) 0b00000001);
        message.add((byte) 0x00);
        message.add((byte) 0x04);

        SocketHelper.addString(message, nick);

        DataOutputStream dos = new DataOutputStream(this.serverManagerSocket.getOutputStream());
        dos.write(SocketHelper.toByteArray(message), 0, message.size());
    }

    @Override
    public void whitelistPlayer(String nick) throws IOException {
        if (this.serverManagerSocket == null) return;
        ArrayList<Byte> message = new ArrayList<>();

        // op player header
        message.add((byte) 0b001_0_0000);
        message.add((byte) 0b00000001);
        message.add((byte) 0x00);
        message.add((byte) 0x03);

        SocketHelper.addString(message, nick);

        DataOutputStream dos = new DataOutputStream(this.serverManagerSocket.getOutputStream());
        dos.write(SocketHelper.toByteArray(message), 0, message.size());
    }

    @Override
    public void stopServer(ServerStopNotifier onServerStop) throws IOException {
        if (this.serverManagerSocket == null) return;
        ArrayList<Byte> message = new ArrayList<>();

        // stop server header
        message.add((byte) 0b001_0_0000);
        message.add((byte) 0b00000001);
        message.add((byte) 0x00);
        message.add((byte) 0x01);

        DataOutputStream dos = new DataOutputStream(this.serverManagerSocket.getOutputStream());
        dos.write(SocketHelper.toByteArray(message), 0, message.size());

        this.serverManagerSocket.close();
        this.serverManagerSocket = null;
        // TODO onServerStop?
    }
}
