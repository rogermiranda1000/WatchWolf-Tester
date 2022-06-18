package com.rogermiranda1000.watchwolf.tester;

import com.rogermiranda1000.watchwolf.entities.*;
import com.rogermiranda1000.watchwolf.serversmanager.ServerStartNotifier;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Tester implements Runnable, ServerStartNotifier {
    private TesterConnector connector;
    private String serverIp;
    private int serverSocketPort;

    public Tester(Socket serverManagerSocket) {
        this.connector = new TesterConnector(serverManagerSocket);
    }

    @Override
    public void run() {
        try {
            String []ip = this.connector.startServer(this, null, ServerType.Spigot, "1.17.1", new Plugin[]{
                    new UsualPlugin("WorldGuard"),
                    new UsualPlugin("WorldEdit"),
                    new UsualPlugin("MineIt")
            }, new Map[]{}, new ConfigFile[]{}).split(":");
            new Thread(this.connector).start();

            this.serverIp = ip[0];
            this.serverSocketPort = Integer.parseInt(ip[1]) + 1; // the server socket port it's the next of the server port

            System.out.println("Server started, waiting for the 'server up' message");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onServerStart() {
        try {
            System.out.println("Connecting to " + this.serverIp + ":" + this.serverSocketPort + "...");
            this.connector.setServerManagerSocket(new Socket(this.serverIp, this.serverSocketPort));

            // start the test
            this.connector.whitelistPlayer("rogermiranda1000");
            this.connector.opPlayer("rogermiranda1000");

            new Scanner(System.in).nextLine(); // wait till enter
            this.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void close() {
        // try to close the server
        try {
            this.connector.stopServer(null);
        } catch (IOException ignore) {}

        // close the connections
        this.connector.close();
        this.connector = null;
    }

    public static void main(String[] args) {
        try {
            Socket serversManagerSocket = new Socket("127.0.0.1", 8000); // ServersManager
            Tester tester = new Tester(serversManagerSocket);
            tester.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
