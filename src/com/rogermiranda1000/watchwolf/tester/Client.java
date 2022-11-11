package com.rogermiranda1000.watchwolf.tester;

import java.net.Socket;

public class Client {
    private final String username;
    private final Socket clientSocket;

    public Client(String username, Socket clientSocket) {
        this.username = username;
        this.clientSocket = clientSocket;
    }

    public String getUsername() {
        return username;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }
}
