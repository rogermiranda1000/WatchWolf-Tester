package com.rogermiranda1000.watchwolf.tester;

import com.rogermiranda1000.watchwolf.client.MessageNotifier;
import com.rogermiranda1000.watchwolf.entities.*;
import com.rogermiranda1000.watchwolf.serversmanager.ServerErrorNotifier;
import com.rogermiranda1000.watchwolf.serversmanager.ServerStartNotifier;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Tester implements Runnable, ServerStartNotifier {
    public static final ServerErrorNotifier DEFAULT_ERROR_PRINT = (err) -> System.err.println("-- Server error --\n" + err.replaceAll("\\\\n", System.lineSeparator()).replaceAll("\\\\t", "\t"));

    private TesterConnector connector;
    private String serverIp;
    private int serverPort, serverSocketPort;

    private Runnable onServerReady;
    private ServerErrorNotifier onError;
    private final ServerType mcType;
    private final String version;
    private final Plugin testedPlugin;
    private final Plugin[] extraPlugins;
    private final Map[] maps;
    private final ConfigFile[] configFiles;
    private final String[] clientNames;

    public Tester(Socket serverManagerSocket, ServerType mcType, String version, Plugin testedPlugin, Plugin[] extraPlugins, Map[] maps, ConfigFile[] configFiles, Socket clientsManagerSocket, String[] clientNames, boolean overrideSync) {
        this.connector = new TesterConnector(serverManagerSocket, clientsManagerSocket, overrideSync);

        this.mcType = mcType;
        this.version = version;
        this.testedPlugin = testedPlugin;
        this.extraPlugins = extraPlugins;
        this.maps = maps;
        this.configFiles = configFiles;
        this.clientNames = clientNames;
    }

    public Tester setOnServerReady(ServerStartNotifier onServerReady) {
        this.onServerReady = onServerReady::onServerStart;
        return this;
    }

    public Tester setOnServerReady(Consumer<TesterConnector> onServerReady) {
        this.onServerReady = ()->onServerReady.accept(this.getConnector());
        return this;
    }

    public Tester setOnServerError(ServerErrorNotifier onError) {
        this.onError = onError;
        return this;
    }

    @Override
    public void run() {
        try {
            List<Plugin> serverPlugins = Arrays.asList(this.extraPlugins);
            serverPlugins.add(testedPlugin);
            String []ip = this.connector.startServer(this, this.onError, this.mcType, this.version, serverPlugins.toArray(new Plugin[0]), this.maps, this.configFiles).split(":");
            new Thread(this.connector).start();

            this.serverIp = ip[0];
            this.serverPort = Integer.parseInt(ip[1]);
            this.serverSocketPort = this.serverPort + 1; // the server socket port it's the next of the server port

            System.out.println("Server started (" + ip[0] + ":" + ip[1] + "), waiting for the 'server up' message");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onServerStart() {
        // connect to the server socket
        try {
            System.out.println("Connecting to " + this.serverIp + ":" + this.serverSocketPort + " (server)...");
            this.connector.setServerManagerSocket(new Socket("127.0.0.1" /* inside docker use this.serverIp; outside use loopback ip (127.0.0.1) */, this.serverSocketPort), this.mcType, this.version);

            // whitelist the players
            for (String client : this.clientNames) this.connector.whitelistPlayer(client);
            try { Thread.sleep(2000); } catch (Exception ignore){} // TODO synchronize with the server (don't connect before the dispatcher whitelist the player!)

            // start the clients
            for (String client : this.clientNames) {
                String ip = this.connector.startClient(client, this.serverIp + ":" + this.serverPort);
                if (ip.length() == 0) throw new IOException("Cannot start client");
                System.out.println("Connecting to " + ip + " (client " + client + ")...");
                String []clientIp = ip.split(":");
                this.connector.setClientSocket(new Socket(clientIp[0], Integer.parseInt(clientIp[1])), client);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // start the test
        if (this.onServerReady != null) this.onServerReady.run();
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
}
