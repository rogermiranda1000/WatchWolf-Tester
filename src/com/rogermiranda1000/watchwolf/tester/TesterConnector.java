package com.rogermiranda1000.watchwolf.tester;

import com.rogermiranda1000.watchwolf.client.ClientPetition;
import com.rogermiranda1000.watchwolf.client.MessageNotifier;
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
import java.util.Collections;
import java.util.HashMap;

public class TesterConnector implements ServerManagerPetition, ServerPetition, ClientManagerPetition, Runnable {
    private final Socket serversManagerSocket, clientsManagerSocket;
    private ServerStartNotifier onServerStart;
    private ServerErrorNotifier onServerError;
    private Socket serverManagerSocket;

    private final HashMap<String,ClientSocket> clients;

    private ServerType mcType;
    private String version;

    /**
     * Ne need to wait n messages of the same type (one for each connected client)
     */
    private final ArrayList<String> messageQueue;
    private final ArrayList<MessageNotifier> messageNotifier;

    public final ServerPetition server = this;
    // TODO client petition with variable

    public TesterConnector(Socket serversManagerSocket, Socket clientsManagerSocket) {
        this.serversManagerSocket = serversManagerSocket;
        this.clientsManagerSocket = clientsManagerSocket;

        this.clients = new HashMap<>();
        this.messageQueue = new ArrayList<>();
        this.messageNotifier = new ArrayList<>();
        SocketData.loadStaticBlock(BlockReader.class);
    }

    public void addOnMessage(MessageNotifier onMessage) {
        this.messageNotifier.add(onMessage);
    }

    public void removeOnMessage(MessageNotifier onMessage) {
        this.messageNotifier.remove(onMessage);
    }

    public void setServerManagerSocket(Socket s, ServerType mcType, String version) {
        this.serverManagerSocket = s;

        this.mcType = mcType;
        this.version = version;
    }

    public void setClientSocket(Socket s, String username) {
        this.clients.put(username, new ClientSocket(username, s));
    }

    public void close() {
        try {
            this.serversManagerSocket.close();
            if (this.serverManagerSocket != null) this.serverManagerSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerPetition getServerPetition() {
        return this.server;
    }

    public ClientPetition getClientPetition(String username) throws ClientNotFoundException {
        ClientSocket client = this.clients.get(username);
        if (client == null) throw new ClientNotFoundException(username + " not in users pool");
        return client;
    }

    public ClientPetition getClientPetition(int index) throws ClientNotFoundException {
        Object []clients = this.clients.values().toArray(); // TODO cache the array
        if (index < 0 || clients.length <= index) throw new ClientNotFoundException("There's only " + clients.length + " users in the pool; asked for number " + index);
        return (ClientPetition) clients[index];
    }

    private Socket getAsyncSocket(int index) throws IndexOutOfBoundsException {
        if (index == 0) return this.serversManagerSocket;
        else {
            try {
                return ((ClientSocket)this.getClientPetition(index - 1)).getSocket();
            } catch (ClientNotFoundException ignore) {
                throw new IndexOutOfBoundsException();
            }
        }
    }

    private void clientMessage(String username, String msg) {
        if (this.messageNotifier.size() == 0) return; // nothing to call; don't bother to log the messages

        String hash = username + ": " + msg;
        int matchesNeeded = this.clients.size()-1;
        if (Collections.frequency(this.messageQueue, hash) >= matchesNeeded) {
            // all the clients got the message
            for (int i = 0; i < matchesNeeded; i++) this.messageQueue.remove(hash);
            for (MessageNotifier notifier : this.messageNotifier) notifier.onMessage(username, msg);
        }
        else {
            // not ready; append to queue
            this.messageQueue.add(hash);
        }
    }

    /**
     * Read async responses
     */
    @Override
    public void run() {
        boolean allClosed = true; // if all the sockets (when IndexOutOfBoundsException is thrown) all are dead
        int index = 0;

        while(true) {
            Socket checkingSocket = null;
            try {
                checkingSocket = this.getAsyncSocket(index++);
            } catch (IndexOutOfBoundsException ignore) {
                if (allClosed) break; // kill the thread

                // reset and start again
                allClosed = true;
                index = 0;
                continue;
            }

            if (checkingSocket.isClosed()) continue;
            allClosed = false;

            synchronized (checkingSocket) {
                int timeout = 1800000; // default Java socket timeout value
                try {
                    timeout = checkingSocket.getSoTimeout();
                } catch (SocketException ignore) {}

                try {
                    checkingSocket.setSoTimeout(1000); // don't stay longer than 1s
                    DataInputStream dis = new DataInputStream(checkingSocket.getInputStream());
                    this.processAsyncReturn(SocketHelper.readShort(dis), dis);
                } catch (EOFException | SocketException | SocketTimeoutException ignore) {
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        checkingSocket.setSoTimeout(timeout);
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
        String error, username, message;
        switch (header) {
            /* -- SERVERS MANAGER ASYNC RETURN -- */
            case 0b000000000010_1_000: // server started
                if (this.onServerStart != null) this.onServerStart.onServerStart();
                else System.out.println("Server started, but notifier not setted");
                break;

            case 0b000000000011_1_000: // error
                error = SocketHelper.readString(dis); // even if no error notifier, we need to remove the string from the socket
                if (this.onServerError != null) this.onServerError.onError(error);
                break;

            /* -- CLIENT ASYNC RETURN -- */
            case 0b000000000011_1_011: // message
                username = SocketHelper.readString(dis);
                message = SocketHelper.readString(dis);
                this.clientMessage(username, message);
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
