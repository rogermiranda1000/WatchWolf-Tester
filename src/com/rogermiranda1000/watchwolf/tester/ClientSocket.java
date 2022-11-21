package com.rogermiranda1000.watchwolf.tester;

import com.rogermiranda1000.watchwolf.client.ClientPetition;
import com.rogermiranda1000.watchwolf.entities.Message;
import com.rogermiranda1000.watchwolf.entities.Position;
import com.rogermiranda1000.watchwolf.entities.items.Item;

import java.io.IOException;
import java.net.Socket;

public class ClientSocket implements ClientPetition {
    private final String username;
    private final Socket socket;

    public ClientSocket(String username, Socket socket) {
        this.username = username;
        this.socket = socket;
    }

    public String getClientUsername() {
        return this.username;
    }

    protected Socket getSocket() {
        return this.socket;
    }

    @Override
    public void sendMessage(String msg) throws IOException {
        Message message = new Message(this.socket);

        // send message header
        message.add((short) 0b000000000011_0_011);

        message.add(msg);

        message.send();
    }

    @Override
    public void runCommand(String cmd) throws IOException {
        Message message = new Message(this.socket);

        // send command header
        message.add((short) 0b000000000100_0_011);

        message.add(cmd);

        message.send();
    }

    @Override
    public void breakBlock(Position block) throws IOException {
        Message message = new Message(this.socket);

        // break block header
        message.add((short) 0b000000000101_0_011);

        message.add(block);

        message.send();
    }

    @Override
    public void equipItemInHand(Item item) throws IOException {
        Message message = new Message(this.socket);

        // equip in hand header
        message.add((short) 0b000000000110_0_011);

        message.add(item);

        message.send();
    }
}
