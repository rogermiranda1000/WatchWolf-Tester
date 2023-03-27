package dev.watchwolf.tester;

import dev.watchwolf.client.MessageNotifier;
import dev.watchwolf.entities.*;
import dev.watchwolf.entities.blocks.BlockReader;
import dev.watchwolf.server.ServerPetition;
import dev.watchwolf.server.ServerStopNotifier;
import dev.watchwolf.clientsmanager.ClientManagerPetition;
import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.entities.Entity;
import dev.watchwolf.entities.entities.EntityType;
import dev.watchwolf.entities.files.ConfigFile;
import dev.watchwolf.entities.files.Plugin;
import dev.watchwolf.entities.items.Item;
import dev.watchwolf.serversmanager.ServerErrorNotifier;
import dev.watchwolf.serversmanager.ServerManagerPetition;
import dev.watchwolf.serversmanager.ServerStartNotifier;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class TesterConnector implements ServerManagerPetition, ServerPetition, ClientManagerPetition, Runnable, AsyncPetitionResolver, SynchronizationManager {
    private final Socket serversManagerSocket, clientsManagerSocket;
    private ServerStartNotifier onServerStart;
    private ServerErrorNotifier onServerError;
    private Socket serverManagerSocket;

    private final HashMap<String,ExtendedClientPetition> clients;

    private ServerType mcType;
    private String version;

    private final boolean overrideSync;
    private Petition lastSynchronization;

    /**
     * Ne need to wait n messages of the same type (one for each connected client)
     */
    private final ArrayList<String> messageQueue;
    private final ArrayList<MessageNotifier> messageNotifier;

    public final ServerPetition server = this;
    // TODO client petition with variable

    public TesterConnector(Socket serversManagerSocket, Socket clientsManagerSocket, boolean overrideSync) {
        this.serversManagerSocket = serversManagerSocket;
        this.clientsManagerSocket = clientsManagerSocket;
        this.overrideSync = overrideSync;

        this.clients = new HashMap<>();
        this.messageQueue = new ArrayList<>();
        this.messageNotifier = new ArrayList<>();
        SocketData.loadStaticBlock(BlockReader.class);
        SocketData.loadStaticBlock(EntityType.class);
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
        this.clients.put(username, new ExtendedClientSocket(username, s, this, this, this));
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

    public String []getClients() {
        return this.clients.keySet().toArray(new String[0]);
    }

    public ExtendedClientPetition getClientPetition(String username) throws ClientNotFoundException {
        ExtendedClientPetition client = this.clients.get(username);
        if (client == null) throw new ClientNotFoundException(username + " not in users pool");
        return client;
    }

    public ExtendedClientPetition getClientPetition(int index) throws ClientNotFoundException {
        Object []clients = this.clients.values().toArray(); // TODO cache the array
        if (index < 0 || clients.length <= index) throw new ClientNotFoundException("There's only " + clients.length + " users in the pool; asked for number " + index);
        return (ExtendedClientPetition) clients[index];
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

    public void processAsyncReturn(int header, DataInputStream dis) throws IOException {
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
    public String startServer(ServerStartNotifier onServerStart, ServerErrorNotifier onError, ServerType mcType, String version, Plugin[] plugins, ConfigFile[] configFiles) throws IOException {
        this.onServerStart = onServerStart;
        this.onServerError = onError;

        ArrayList<Byte> message = new ArrayList<>();

        // start server header
        message.add((byte) 0b0001_0_000);
        message.add((byte) 0b00000000);

        SocketHelper.addString(message, mcType.name());
        SocketHelper.addString(message, version);

        SocketHelper.addArray(message, plugins);

        SocketHelper.addArray(message, configFiles);

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

        this.requestSynchronization((ServerPetition)this);

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

        this.requestSynchronization((ServerPetition)this);

        Message message = new Message(this.serverManagerSocket);

        // whitelist player header
        message.add((byte) 0b0001_0_001);
        message.add((byte) 0b00000000);
        message.add((short) 0x0003);

        message.add(nick);

        message.send();
    }

    @Override
    public Position getPlayerPosition(String nick) throws IOException {
        if (this.serverManagerSocket == null) return null;

        this.requestSynchronization((ServerPetition)this);

        Message message = new Message(this.serverManagerSocket);

        // get player position header
        message.add((byte) 0b0001_0_001);
        message.add((byte) 0b00000000);
        message.add((short) 0x0007);

        message.add(nick);

        synchronized (this.serverManagerSocket) { // response with return -> reserve the socket before the thread does
            message.send();

            // read response
            DataInputStream dis = new DataInputStream(this.serverManagerSocket.getInputStream());
            int r = SocketHelper.readShort(dis);
            while (r != 0b000000000001_1_001) {
                this.processAsyncReturn(r, dis); // expected return, found async return from another request
                r = SocketHelper.readShort(dis);
            }
            if (SocketHelper.readShort(dis) != 0x0007) throw new IOException("Expected response from 0x0007 operation.");
            return (Position) SocketData.readSocketData(dis, Position.class);
        }
    }

    @Override
    public float getPlayerPitch(String nick) throws IOException {
        if (this.serverManagerSocket == null) return 0.0f;

        this.requestSynchronization((ServerPetition)this);

        Message message = new Message(this.serverManagerSocket);

        // get player pitch header
        message.add((byte) 0b0001_0_001);
        message.add((byte) 0b00000000);
        message.add((short) 0x000D);

        message.add(nick);

        synchronized (this.serverManagerSocket) { // response with return -> reserve the socket before the thread does
            message.send();

            // read response
            DataInputStream dis = new DataInputStream(this.serverManagerSocket.getInputStream());
            int r = SocketHelper.readShort(dis);
            while (r != 0b000000000001_1_001) {
                this.processAsyncReturn(r, dis); // expected return, found async return from another request
                r = SocketHelper.readShort(dis);
            }
            if (SocketHelper.readShort(dis) != 0x000D) throw new IOException("Expected response from 0x000D operation.");
            return (float)SocketHelper.readDouble(dis);
        }
    }

    @Override
    public float getPlayerYaw(String nick) throws IOException {
        if (this.serverManagerSocket == null) return 0.0f;

        this.requestSynchronization((ServerPetition)this);

        Message message = new Message(this.serverManagerSocket);

        // get player yaw header
        message.add((byte) 0b0001_0_001);
        message.add((byte) 0b00000000);
        message.add((short) 0x000E);

        message.add(nick);

        synchronized (this.serverManagerSocket) { // response with return -> reserve the socket before the thread does
            message.send();

            // read response
            DataInputStream dis = new DataInputStream(this.serverManagerSocket.getInputStream());
            int r = SocketHelper.readShort(dis);
            while (r != 0b000000000001_1_001) {
                this.processAsyncReturn(r, dis); // expected return, found async return from another request
                r = SocketHelper.readShort(dis);
            }
            if (SocketHelper.readShort(dis) != 0x000E) throw new IOException("Expected response from 0x000E operation.");
            return (float)SocketHelper.readDouble(dis);
        }
    }

    @Override
    public void giveItem(String nick, Item item) throws IOException {
        if (this.serverManagerSocket == null) return;

        this.requestSynchronization((ServerPetition)this);

        Message message = new Message(this.serverManagerSocket);

        // give item header
        message.add((byte) 0b0001_0_001);
        message.add((byte) 0b00000000);
        message.add((short) 0x0008);

        message.add(nick);
        message.add(item);

        message.send();
    }

    @Override
    public void tp(String nick, Position pos) throws IOException {
        if (this.serverManagerSocket == null) return;

        this.requestSynchronization((ServerPetition)this);

        Message message = new Message(this.serverManagerSocket);

        // tp player header
        message.add((byte) 0b0001_0_001);
        message.add((byte) 0b00000000);
        message.add((short) 0x000C);

        message.add(nick);
        message.add(pos);

        message.send();
    }

    @Override
    public Container getInventory(String nick) throws IOException {
        if (this.serverManagerSocket == null) return null;

        this.requestSynchronization((ServerPetition)this);

        Message message = new Message(this.serverManagerSocket);

        // get inventory header
        message.add((byte) 0b0001_0_001);
        message.add((byte) 0b00000000);
        message.add((short) 0x000F);

        message.add(nick);

        synchronized (this.serverManagerSocket) { // response with return -> reserve the socket before the thread does
            message.send();

            // read response
            DataInputStream dis = new DataInputStream(this.serverManagerSocket.getInputStream());
            int r = SocketHelper.readShort(dis);
            while (r != 0b000000000001_1_001) {
                this.processAsyncReturn(r, dis); // expected return, found async return from another request
                r = SocketHelper.readShort(dis);
            }
            if (SocketHelper.readShort(dis) != 0x000F) throw new IOException("Expected response from 0x000F operation.");

            return (Container) SocketData.readSocketData(dis, Container.class);
        }
    }

    @Override
    public String[] getPlayers() throws IOException {
        if (this.serverManagerSocket == null) return null;

        this.requestSynchronization((ServerPetition)this);

        Message message = new Message(this.serverManagerSocket);

        // get players header
        message.add((byte) 0b0001_0_001);
        message.add((byte) 0b00000000);
        message.add((short) 0x000A);

        synchronized (this.serverManagerSocket) { // response with return -> reserve the socket before the thread does
            message.send();

            // read response
            DataInputStream dis = new DataInputStream(this.serverManagerSocket.getInputStream());
            int r = SocketHelper.readShort(dis);
            while (r != 0b000000000001_1_001) {
                this.processAsyncReturn(r, dis); // expected return, found async return from another request
                r = SocketHelper.readShort(dis);
            }
            if (SocketHelper.readShort(dis) != 0x000A) throw new IOException("Expected response from 0x000A operation.");

            // TODO move to helper
            int size = SocketHelper.readShort(dis);
            String []players = new String[size];
            for (int n = 0; n < size; n++) players[n] = SocketHelper.readString(dis);
            return players;
        }
    }

    @Override
    public void stopServer(ServerStopNotifier onServerStop) throws IOException {
        if (this.serverManagerSocket == null) return;

        this.requestSynchronization((ServerPetition)this);

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

        this.requestSynchronization((ServerPetition)this);

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

        this.requestSynchronization((ServerPetition)this);

        Message message = new Message(this.serverManagerSocket);

        // get block header
        message.add((byte) 0b0001_0_001);
        message.add((byte) 0b00000000);
        message.add((short) 0x0006);

        message.add(position);

        synchronized (this.serverManagerSocket) { // response with return -> reserve the socket before the thread does
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
    public String runCommand(String cmd) throws IOException {
        if (this.serverManagerSocket == null) return "";

        this.requestSynchronization((ServerPetition)this);

        Message message = new Message(this.serverManagerSocket);

        // run command header
        message.add((byte) 0b0001_0_001);
        message.add((byte) 0b00000000);
        message.add((short) 0x0009);

        message.add(cmd);

        synchronized (this.serverManagerSocket) {
            message.send();

            // read response
            DataInputStream dis = new DataInputStream(this.serverManagerSocket.getInputStream());
            int r = SocketHelper.readShort(dis);
            while (r != 0b000000000001_1_001) {
                this.processAsyncReturn(r, dis); // expected return, found async return from another request
                r = SocketHelper.readShort(dis);
            }
            if (SocketHelper.readShort(dis) != 0x0009) throw new IOException("Expected response from 0x0009 operation.");
            return SocketHelper.readString(dis);
        }
    }

    @Override
    public Entity[] getEntities(Position center, double radius) throws IOException {
        if (this.serverManagerSocket == null) return null;

        this.requestSynchronization((ServerPetition)this);

        Message message = new Message(this.serverManagerSocket);

        // get players header
        message.add((byte) 0b0001_0_001);
        message.add((byte) 0b00000000);
        message.add((short) 0x0010);

        message.add(center);
        message.add(radius);

        synchronized (this.serverManagerSocket) { // response with return -> reserve the socket before the thread does
            message.send();

            // read response
            DataInputStream dis = new DataInputStream(this.serverManagerSocket.getInputStream());
            int r = SocketHelper.readShort(dis);
            while (r != 0b000000000001_1_001) {
                this.processAsyncReturn(r, dis); // expected return, found async return from another request
                r = SocketHelper.readShort(dis);
            }
            if (SocketHelper.readShort(dis) != 0x0010) throw new IOException("Expected response from 0x0010 operation.");

            // TODO move to helper
            int size = SocketHelper.readShort(dis);
            Entity []entities = new Entity[size];
            for (int n = 0; n < size; n++) entities[n] = (Entity) SocketData.readSocketData(dis, Entity.class);
            return entities;
        }
    }

    @Override
    public Entity spawnEntity(Entity e) throws IOException {
        if (this.serverManagerSocket == null) return null;

        this.requestSynchronization((ServerPetition)this);

        Message message = new Message(this.serverManagerSocket);

        // spawn entity header
        message.add((byte) 0b0001_0_001);
        message.add((byte) 0b00000000);
        message.add((short) 0x0011);

        message.add(e);

        synchronized (this.serverManagerSocket) { // response with return -> reserve the socket before the thread does
            message.send();

            // read response
            DataInputStream dis = new DataInputStream(this.serverManagerSocket.getInputStream());
            int r = SocketHelper.readShort(dis);
            while (r != 0b000000000001_1_001) {
                this.processAsyncReturn(r, dis); // expected return, found async return from another request
                r = SocketHelper.readShort(dis);
            }
            if (SocketHelper.readShort(dis) != 0x0011) throw new IOException("Expected response from 0x0011 operation.");
            return (Entity) SocketData.readSocketData(dis, Entity.class);
        }
    }

    @Override
    public Entity getEntity(String UUID) throws IOException {
        if (this.serverManagerSocket == null) return null;

        this.requestSynchronization((ServerPetition)this);

        Message message = new Message(this.serverManagerSocket);

        // get entity by uuid header
        message.add((byte) 0b0001_0_001);
        message.add((byte) 0b00000000);
        message.add((short) 0x0012);

        message.add(UUID);

        synchronized (this.serverManagerSocket) { // response with return -> reserve the socket before the thread does
            message.send();

            // read response
            DataInputStream dis = new DataInputStream(this.serverManagerSocket.getInputStream());
            int r = SocketHelper.readShort(dis);
            while (r != 0b000000000001_1_001) {
                this.processAsyncReturn(r, dis); // expected return, found async return from another request
                r = SocketHelper.readShort(dis);
            }
            if (SocketHelper.readShort(dis) != 0x0012) throw new IOException("Expected response from 0x0012 operation.");
            return (Entity) SocketData.readSocketData(dis, Entity.class);
        }
    }

    @Override
    public void synchronize() throws IOException {
        if (this.serverManagerSocket == null) return;

        this.requestSynchronization((ServerPetition)this);

        Message message = new Message(this.serverManagerSocket);

        // synchronize header
        message.add((byte) 0b0001_0_001);
        message.add((byte) 0b00000000);
        message.add((short) 0x000B);

        synchronized (this.serverManagerSocket) { // response with return -> reserve the socket before the thread does
            message.send();

            // read response
            DataInputStream dis = new DataInputStream(this.serverManagerSocket.getInputStream());
            int r = SocketHelper.readShort(dis);
            while (r != 0b000000000001_1_001) {
                this.processAsyncReturn(r, dis); // expected return, found async return from another request
                r = SocketHelper.readShort(dis);
            }
            if (SocketHelper.readShort(dis) != 0x000B) throw new IOException("Expected response from 0x000B operation.");
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

    @Override
    public void requestSynchronization(Petition current) {
        if (this.overrideSync) return; // ignore synchronization

        if (this.lastSynchronization == null || this.lastSynchronization == current) {
            // already synchronized
            this.lastSynchronization = current;
            return;
        }

        try {
            this.lastSynchronization.synchronize();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // synchronized
        this.lastSynchronization = current;
    }

    @Override
    public String toString() {
        return "TesterConnector{" + this.getServerType() + " " + this.getServerVersion() + "}";
    }
}
