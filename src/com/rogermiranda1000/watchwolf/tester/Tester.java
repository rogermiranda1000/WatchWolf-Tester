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
            String []ip = this.connector.startServer(this, null, new Map[]{}, new Plugin[]{}, ServerType.Spigot, "1.17.1", new ConfigFile[]{}).split(":");
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
            // TODO start the test
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
            Socket serversManagerSocket = new Socket("127.0.0.1", 8000); // ServersManager
            Tester tester = new Tester(serversManagerSocket);
            tester.run();

            tester.connector.whitelistPlayer("rogermiranda1000");
            tester.connector.opPlayer("rogermiranda1000");

            new Scanner(System.in).nextLine(); // wait till enter
            tester.connector.stopServer(null);

            tester.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
