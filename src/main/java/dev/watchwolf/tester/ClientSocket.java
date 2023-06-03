package dev.watchwolf.tester;

import dev.watchwolf.client.ClientPetition;
import dev.watchwolf.entities.Message;
import dev.watchwolf.entities.Position;
import dev.watchwolf.entities.SocketHelper;
import dev.watchwolf.entities.entities.Entity;
import dev.watchwolf.entities.items.Item;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientSocket implements ClientPetition {
    protected final String username;
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
    public String runCommand(String cmd, int timeout) throws IOException {
        this.syncManager.requestSynchronization(this);

        Message message = new Message(this.socket);

        // send command header
        message.add((short) 0b000000000100_0_011);

        message.add(cmd);
        message.add((short) timeout);

        synchronized (this.socket) {
            message.send();

            // read response
            DataInputStream dis = new DataInputStream(this.socket.getInputStream());
            int r = SocketHelper.readShort(dis);
            while (r != 0b000000000100_1_011) {
                this.asyncResolver.processAsyncReturn(r, dis); // expected return, found async return from another request
                r = SocketHelper.readShort(dis);
            }
            return SocketHelper.readString(dis);
        }
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
    public void setBlock(Position block) throws IOException {
        this.syncManager.requestSynchronization(this);

        Message message = new Message(this.socket);

        // break block header
        message.add((short) 0b000000001100_0_011);

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
    public void attack(String uuid) throws IOException {
        this.syncManager.requestSynchronization(this);

        Message message = new Message(this.socket);

        // look at header
        message.add((short) 0b000000001101_0_011);

        message.add(uuid);

        message.send();
    }

    @Override
    public String getVersion() throws IOException {
        // no need to sync

        Message message = new Message(this.socket);

        // get version header
        message.add((short) 0b111111111111_0_011);

        synchronized (this.socket) {
            message.send();

            // TODO if none got, then it's <0.1.22

            // read response
            DataInputStream dis = new DataInputStream(this.socket.getInputStream());
            int r = SocketHelper.readShort(dis);
            while (r != 0b111111111111_1_011) {
                this.asyncResolver.processAsyncReturn(r, dis); // expected return, found async return from another request
                r = SocketHelper.readShort(dis);
            }
            return SocketHelper.readString(dis);
        }
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
