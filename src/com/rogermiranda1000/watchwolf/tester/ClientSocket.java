package com.rogermiranda1000.watchwolf.tester;

import com.rogermiranda1000.watchwolf.client.ClientPetition;
import com.rogermiranda1000.watchwolf.entities.Message;
import com.rogermiranda1000.watchwolf.entities.Position;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import com.rogermiranda1000.watchwolf.entities.items.Item;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientSocket implements ClientPetition {
    private final String username;
    private final Socket socket;
    private final AsyncPetitionResolver asyncResolver;
    private final SynchronizationManager syncManager;

    public ClientSocket(String username, Socket socket, AsyncPetitionResolver asyncResolver, SynchronizationManager syncManager) {
        this.username = username;
        this.socket = socket;
        this.asyncResolver = asyncResolver;
        this.syncManager = syncManager;
    }

    public String getClientUsername() {
        return this.username;
    }

    protected Socket getSocket() {
        return this.socket;
    }

    @Override
    public void sendMessage(String msg) throws IOException {
        this.syncManager.requestSynchronization(this);

        Message message = new Message(this.socket);

        // send message header
        message.add((short) 0b000000000011_0_011);

        message.add(msg);

        message.send();
    }

    @Override
    public void runCommand(String cmd) throws IOException {
        this.syncManager.requestSynchronization(this);

        Message message = new Message(this.socket);

        // send command header
        message.add((short) 0b000000000100_0_011);

        message.add(cmd);

        message.send();
    }

    @Override
    public void breakBlock(Position block) throws IOException {
        this.syncManager.requestSynchronization(this);

        Message message = new Message(this.socket);

        // break block header
        message.add((short) 0b000000000101_0_011);

        message.add(block);

        message.send();
    }

    @Override
    public void equipItemInHand(Item item) throws IOException {
        this.syncManager.requestSynchronization(this);

        Message message = new Message(this.socket);

        // equip in hand header
        message.add((short) 0b000000000110_0_011);

        message.add(item);

        message.send();
    }

    @Override
    public void moveTo(Position pos) throws IOException {
        this.syncManager.requestSynchronization(this);

        Message message = new Message(this.socket);

        // move at header
        message.add((short) 0b000000000111_0_011);

        message.add(pos);

        message.send();
    }

    @Override
    public void lookAt(float pitch, float yaw) throws IOException {
        this.syncManager.requestSynchronization(this);

        Message message = new Message(this.socket);

        // look at header
        message.add((short) 0b000000001000_0_011);

        message.add(pitch);
        message.add(yaw);

        message.send();
    }

    @Override
    public void hit() throws IOException {
        this.syncManager.requestSynchronization(this);

        Message message = new Message(this.socket);

        // hit header
        message.add((short) 0b000000001010_0_011);

        message.send();
    }

    @Override
    public void use() throws IOException {
        this.syncManager.requestSynchronization(this);

        Message message = new Message(this.socket);

        // use header
        message.add((short) 0b000000001011_0_011);

        message.send();
    }

    @Override
    public void synchronize() throws IOException {
        this.syncManager.requestSynchronization(this);

        Message message = new Message(this.socket);

        // synchronize header
        message.add((short) 0b000000001001_0_011);

        synchronized (this.socket) {
            message.send();

            // read response
            DataInputStream dis = new DataInputStream(this.socket.getInputStream());
            int r = SocketHelper.readShort(dis);
            while (r != 0b000000001001_1_011) {
                this.asyncResolver.processAsyncReturn(r, dis); // expected return, found async return from another request
                r = SocketHelper.readShort(dis);
            }
        }
    }
}
