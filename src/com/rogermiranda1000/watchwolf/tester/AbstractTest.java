package com.rogermiranda1000.watchwolf.tester;

import com.rogermiranda1000.watchwolf.entities.ConfigFile;
import com.rogermiranda1000.watchwolf.entities.Map;
import com.rogermiranda1000.watchwolf.entities.Plugin;
import com.rogermiranda1000.watchwolf.entities.ServerType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

abstract public class AbstractTest {
    private static Tester tester;
    public static TesterConnector connector;

    public abstract File getConfigFile(); // TODO how static abstract

    // TODO move to file
    public static final String []serversManagerIP = "127.0.0.1:8000".split(":");
    private static final ServerType serverType = ServerType.Spigot;
    private static final String serverVersion = "1.18.2";

    @BeforeAll
    public static void setup() throws IOException, InterruptedException {
        Socket serversManagerSocket = new Socket(serversManagerIP[0], Integer.parseInt(serversManagerIP[1])); // ServersManager socket

        System.out.println("Starting test for " + serverType.name() + " " + AbstractTest.serverVersion);
        AbstractTest.tester = new Tester(serversManagerSocket, AbstractTest.serverType, AbstractTest.serverVersion, new Plugin[]{}, new Map[]{}, new ConfigFile[]{}) // TODO rest of variables
                .setOnServerError(Tester.DEFAULT_ERROR_PRINT); // TODO report to JUnit

        final Object waitForStartup = new Object();
        AbstractTest.tester.setOnServerStart((connector) -> {
            synchronized (waitForStartup) {
                AbstractTest.connector = connector;
                waitForStartup.notify();
            }
        });

        synchronized (waitForStartup) {
            AbstractTest.tester.run();

            waitForStartup.wait();
            // at this point the connector is ready; end the setup and start the tests
        }
    }

    @AfterAll
    public static void stop() {
        AbstractTest.tester.close();
    }
}
