package com.rogermiranda1000.watchwolf.tester;

import com.rogermiranda1000.watchwolf.client.ClientPetition;
import com.rogermiranda1000.watchwolf.entities.Message;

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

        // start client header
        message.add((short) 0b000000000011_0_011);

        message.add(msg);

        message.send();
    }

    @Override
    public void runCommand(String cmd) throws IOException {
        Message message = new Message(this.socket);

        // start client header
        message.add((short) 0b000000000100_0_011);

        message.add(cmd);

        message.send();
    }
}
