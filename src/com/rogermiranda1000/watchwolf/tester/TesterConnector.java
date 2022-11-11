package com.rogermiranda1000.watchwolf.tester;

import com.rogermiranda1000.watchwolf.clientsmanager.ClientManagerPetition;
import com.rogermiranda1000.watchwolf.entities.*;
import com.rogermiranda1000.watchwolf.entities.blocks.Block;
import com.rogermiranda1000.watchwolf.server.ServerPetition;
import com.rogermiranda1000.watchwolf.server.ServerStopNotifier;
import com.rogermiranda1000.watchwolf.serversmanager.*;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;

public class TesterConnector implements ServerManagerPetition, ServerPetition, ClientManagerPetition, Runnable {
    private final Socket serversManagerSocket, clientsManagerSocket;
    private ServerStartNotifier onServerStart;
    private ServerErrorNotifier onServerError;
    private Socket serverManagerSocket;

    private ArrayList<Client> clients;

    private ServerType mcType;
    private String version;

    public TesterConnector(Socket serversManagerSocket, Socket clientsManagerSocket) {
        this.serversManagerSocket = serversManagerSocket;
        this.clientsManagerSocket = clientsManagerSocket;

        this.clients = new ArrayList<>();
        SocketData.loadStaticBlock(BlockReader.class);
    }

    public void setServerManagerSocket(Socket s, ServerType mcType, String version) {
        this.serverManagerSocket = s;

        this.mcType = mcType;
        this.version = version;
    }

    public void setClientSocket(Socket s, String username) {
        this.clients.add(new Client(username, s));
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
                int timeout = 1800000; // default Java socket timeout value
                try {
                    timeout = this.serversManagerSocket.getSoTimeout();
                } catch (SocketException ignore) {}

                try {
                    this.serversManagerSocket.setSoTimeout(1000); // don't stay longer than 1s
                    DataInputStream dis = new DataInputStream(this.serversManagerSocket.getInputStream());
                    this.processAsyncReturn(SocketHelper.readShort(dis), dis);
                } catch (EOFException | SocketException | SocketTimeoutException ignore) {
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        this.serversManagerSocket.setSoTimeout(timeout);
                    } catch (SocketException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            try {
                Thread.sleep(1000); // give some margin for the rest of the requests
            } catch (InterruptedException ignore) {}
        }
    }

    private void processAsyncReturn(int header, DataInputStream dis) throws IOException {
        switch (header) {
            case 0b000000000010_1_000: // server started
                if (this.onServerStart != null) this.onServerStart.onServerStart();
                else System.out.println("Server started, but notifier not setted");
                break;

            case 0b000000000011_1_000: // error
                String error = SocketHelper.readString(dis); // even if no error notifier, we need to remove the string from the socket
                if (this.onServerError != null) this.onServerError.onError(error);
                break;

            default:
                System.out.println("Unknown request: " + header);
        }
    }

    /* EXTRA INTERFACES */
    public ServerType getServerType() {
        return this.mcType;
    }

    public String getServerVersion() {
        return this.version;
    }

    /* INTERFACES */
    @Override
    public String startServer(ServerStartNotifier onServerStart, ServerErrorNotifier onError, ServerType mcType, String version, Plugin[] plugins, Map[] maps, ConfigFile[] configFiles) throws IOException {
        this.onServerStart = onServerStart;
        this.onServerError = onError;

        ArrayList<Byte> message = new ArrayList<>();

        // start server header
        message.add((byte) 0b0001_0_000);
        message.add((byte) 0b00000000);

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
            int r = SocketHelper.readShort(dis);
            while (r != 0b000000000001_1_000) { // server started response
                this.processAsyncReturn(r, dis); // expected return, found async return from another request
                r = SocketHelper.readShort(dis);
            }
            return SocketHelper.readString(dis); // TODO if string is "" -> error
        }
    }

    @Override
    public void opPlayer(String nick) throws IOException {
        if (this.serverManagerSocket == null) return;
        Message message = new Message(this.serverManagerSocket);

        // op player header
        message.add((byte) 0b0001_0_001);
        message.add((byte) 0b00000000);
        message.add((short) 0x0004);

        message.add(nick);

        message.send();
    }

    @Override
    public void whitelistPlayer(String nick) throws IOException {
        if (this.serverManagerSocket == null) return;
        Message message = new Message(this.serverManagerSocket);

        // op player header
        message.add((byte) 0b0001_0_001);
        message.add((byte) 0b00000000);
        message.add((short) 0x0003);

        message.add(nick);

        message.send();
    }

    @Override
    public void stopServer(ServerStopNotifier onServerStop) throws IOException {
        if (this.serverManagerSocket == null) return;
        Message message = new Message(this.serverManagerSocket);

        // stop server header
        message.add((byte) 0b0001_0_001);
        message.add((byte) 0b00000000);
        message.add((short) 0x0001);

        message.send();

        this.serverManagerSocket.close();
        this.serverManagerSocket = null;
        // TODO onServerStop?
    }

    @Override
    public void setBlock(Position position, Block block) throws IOException {
        if (this.serverManagerSocket == null) return;
        Message message = new Message(this.serverManagerSocket);

        // set block header
        message.add((byte) 0b0001_0_001);
        message.add((byte) 0b00000000);
        message.add((short) 0x0005);

        message.add(position);
        message.add(block);

        message.send();
    }

    @Override
    public Block getBlock(Position position) throws IOException {
        if (this.serverManagerSocket == null) return null;
        Message message = new Message(this.serverManagerSocket);

        // get block header
        message.add((byte) 0b0001_0_001);
        message.add((byte) 0b00000000);
        message.add((short) 0x0006);

        message.add(position);

        synchronized (this.serversManagerSocket) { // response with return -> reserve the socket before the thread does
            message.send();

            // read response
            DataInputStream dis = new DataInputStream(this.serverManagerSocket.getInputStream());
            int r = SocketHelper.readShort(dis);
            while (r != 0b000000000001_1_001) {
                this.processAsyncReturn(r, dis); // expected return, found async return from another request
                r = SocketHelper.readShort(dis);
            }
            if (SocketHelper.readShort(dis) != 0x0006) throw new IOException("Expected response from 0x0006 operation.");
            return (Block) SocketData.readSocketData(dis, Block.class);
        }
    }

    @Override
    public String startClient(String username, String serverIp) throws IOException {
        Message message = new Message(this.clientsManagerSocket);

        // start client header
        message.add((short) 0b000000000001_0_010);

        message.add(username);
        message.add(serverIp);

        synchronized (this.clientsManagerSocket) {
            message.send();

            // read response
            DataInputStream dis = new DataInputStream(this.clientsManagerSocket.getInputStream());
            int r = SocketHelper.readShort(dis);
            while (r != 0b000000000001_1_010) { // server started response
                this.processAsyncReturn(r, dis); // expected return, found async return from another request
                r = SocketHelper.readShort(dis);
            }
            return SocketHelper.readString(dis); // TODO if string is "" -> error
        }
    }
}
