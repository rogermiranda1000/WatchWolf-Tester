package com.rogermiranda1000.watchwolf.tester;

import com.rogermiranda1000.watchwolf.entities.*;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class Tester implements Runnable {
    private TesterConnector connector;

    public Tester(TesterConnector connector) {
        this.connector = connector;
    }

    @Override
    public void run() {
        try {
            String []ip = this.connector.startServer(null, null, new Map[]{}, new Plugin[]{}, ServerType.Spigot, "1.17.1", new ConfigFile[]{}).split(":");
            while (true) {
                try {
                    // TODO connect on 'server started' packet
                    int port = Integer.parseInt(ip[1]) + 1; // the server socket port it's the next of the server port
                    System.out.println("Connecting to " + ip[0] + ":" + port + "...");
                    this.connector.setServerManagerSocket(new Socket(ip[0], port));
                    break;
                } catch (ConnectException ex) {}
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void close() {
        this.connector.close();

        this.connector = null;
    }

    public static void main(String[] args) {
        try {
            Socket serversManagerSocket = new Socket("127.0.0.1", 8000);
            Tester tester = new Tester(new TesterConnector(serversManagerSocket));
            tester.run();
            tester.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
