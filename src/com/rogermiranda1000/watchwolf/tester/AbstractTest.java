package com.rogermiranda1000.watchwolf.tester;

import com.rogermiranda1000.watchwolf.entities.ConfigFile;
import com.rogermiranda1000.watchwolf.entities.Map;
import com.rogermiranda1000.watchwolf.entities.Plugin;
import com.rogermiranda1000.watchwolf.entities.ServerType;
import org.junit.jupiter.api.extension.*;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;

public class AbstractTest implements TestWatcher, // send feedback
        BeforeAllCallback, AfterAllCallback, // open/close server
        ParameterResolver { // send arguments
    private static class ServerInstance {
        public Tester tester;
        public TesterConnector connector;
    }

    private ArrayList<ServerInstance> servers;

    private UUID testID;

    // TODO move to file
    public final String []serversManagerIP = "127.0.0.1:8000".split(":");
    private final ServerType serverType = ServerType.Spigot;
    private final String []serverVersions = {"1.18.2", "1.17"};

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws IOException {
        this.servers = new ArrayList<>();

        Socket serversManagerSocket = new Socket(serversManagerIP[0], Integer.parseInt(serversManagerIP[1])); // ServersManager socket
        this.testID = UUID.randomUUID();

        Thread []startQueue = new Thread[this.serverVersions.length];
        for (int n = 0; n < this.serverVersions.length; n++) {
            String serverVersion = this.serverVersions[n];
            final ServerInstance server = new ServerInstance();
            this.servers.add(server);

            System.out.println("Starting server for " + serverType.name() + " " + serverVersion + " using ID " + testID.toString());
            server.tester = new Tester(serversManagerSocket, this.serverType, serverVersion, new Plugin[]{}, new Map[]{}, new ConfigFile[]{}) // TODO rest of variables
                    .setOnServerError(Tester.DEFAULT_ERROR_PRINT); // TODO report to JUnit

            final Object waitForStartup = new Object();
            server.tester.setOnServerStart((connector) -> {
                synchronized (waitForStartup) {
                    server.connector = connector;
                    waitForStartup.notify();
                }
            });

            startQueue[n] = new Thread(() -> {
                synchronized (waitForStartup) {
                    server.tester.run();

                    try {
                        waitForStartup.wait();
                        // at this point this connector is ready
                    } catch (InterruptedException ignore) {}
                }
            });
            startQueue[n].start();
        }

        for (Thread t : startQueue) {
            try {
                t.join();
            } catch (InterruptedException ignore) {}
        }
        // at this point the connectors are ready; end the setup and start the tests
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        for (ServerInstance server : this.servers) server.tester.close();

        // TODO send 'done' to website
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        // TODO send 'ok' to website
        System.err.println("Test " + context.getDisplayName() + " succeed");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        // TODO send 'fail' to website
        System.err.println("Test " + context.getDisplayName() + " failed: " + cause.getMessage());
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == TesterConnector.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return this.servers.get(0).connector;
    }

    /**
     * Method to override
     * @return WatchWolf config file
     */
    public File getConfigFile() { throw new UnspecifiedConfigFileException(); }
}
