package com.rogermiranda1000.watchwolf.tester;

import com.rogermiranda1000.watchwolf.entities.*;
import com.rogermiranda1000.watchwolf.serversmanager.ServerErrorNotifier;
import com.rogermiranda1000.watchwolf.serversmanager.ServerStartNotifier;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Tester implements Runnable, ServerStartNotifier {
    public static final ServerErrorNotifier DEFAULT_ERROR_PRINT = (err) -> System.err.println("-- Server error --\n" + err.replaceAll("\\\\n", System.lineSeparator()).replaceAll("\\\\t", "\t"));

    private TesterConnector connector;
    private String serverIp;
    private int serverSocketPort;

    private ServerStartNotifier onServerStart;
    private ServerErrorNotifier onError;
    private final ServerType mcType;
    private final String version;
    private final Plugin[] plugins;
    private final Map[] maps;
    private final ConfigFile[] configFiles;

    public Tester(Socket serverManagerSocket, ServerType mcType, String version, Plugin[] plugins, Map[] maps, ConfigFile[] configFiles) {
        this.connector = new TesterConnector(serverManagerSocket);

        this.mcType = mcType;
        this.version = version;
        this.plugins = plugins;
        this.maps = maps;
        this.configFiles = configFiles;
    }

    public Tester setOnServerStart(ServerStartNotifier onServerStart) {
        this.onServerStart = onServerStart;
        return this;
    }

    public Tester setOnServerError(ServerErrorNotifier onError) {
        this.onError = onError;
        return this;
    }

    @Override
    public void run() {
        try {
            String []ip = this.connector.startServer(this.onServerStart, this.onError, this.mcType, this.version, this.plugins, this.maps, this.configFiles).split(":");
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
        // connect to the server socket
        try {
            System.out.println("Connecting to " + this.serverIp + ":" + this.serverSocketPort + "...");
            this.connector.setServerManagerSocket(new Socket(this.serverIp, this.serverSocketPort));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // start the test
        if (this.onServerStart != null) this.onServerStart.onServerStart();
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

    public TesterConnector getConnector() {
        return this.connector;
    }

    public static void main(String[] args) {
        try {
            Socket serversManagerSocket = new Socket("127.0.0.1", 8000); // ServersManager socket

            Tester tester = new Tester(serversManagerSocket, ServerType.Spigot, "1.8", new Plugin[]{
                    new UsualPlugin("WorldGuard"),
                    new UsualPlugin("WorldEdit"),
                    new UsualPlugin("MineIt"),
                    new UsualPlugin("PortalGun")
            }, new Map[]{}, new ConfigFile[]{})
                    .setOnServerError(Tester.DEFAULT_ERROR_PRINT);

            tester.setOnServerStart(() -> {
                try {
                    TesterConnector connector = tester.getConnector();
                    connector.whitelistPlayer("rogermiranda1000");
                    connector.opPlayer("rogermiranda1000");

                    new Scanner(System.in).nextLine(); // wait till enter
                    tester.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            tester.run(); // all prepared, start the server
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
