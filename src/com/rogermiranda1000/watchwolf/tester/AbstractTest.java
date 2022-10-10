package com.rogermiranda1000.watchwolf.tester;

import com.rogermiranda1000.watchwolf.entities.ConfigFile;
import com.rogermiranda1000.watchwolf.entities.Map;
import com.rogermiranda1000.watchwolf.entities.Plugin;
import com.rogermiranda1000.watchwolf.entities.ServerType;
import org.junit.jupiter.api.extension.*;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.UUID;

public class AbstractTest implements TestWatcher, // send feedback
        BeforeAllCallback, AfterAllCallback, // open/close server
        ParameterResolver { // send arguments
    private Tester tester;
    private TesterConnector connector;

    private UUID testID;

    // TODO move to file
    public final String []serversManagerIP = "127.0.0.1:8000".split(":");
    private final ServerType serverType = ServerType.Spigot;
    private final String serverVersion = "1.18.2";

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws IOException, InterruptedException {
        Socket serversManagerSocket = new Socket(serversManagerIP[0], Integer.parseInt(serversManagerIP[1])); // ServersManager socket
        this.testID = UUID.randomUUID();

        System.out.println("Starting test for " + serverType.name() + " " + this.serverVersion + " using ID " + testID.toString());
        this.tester = new Tester(serversManagerSocket, this.serverType, this.serverVersion, new Plugin[]{}, new Map[]{}, new ConfigFile[]{}) // TODO rest of variables
                .setOnServerError(Tester.DEFAULT_ERROR_PRINT); // TODO report to JUnit

        final Object waitForStartup = new Object();
        this.tester.setOnServerStart((connector) -> {
            synchronized (waitForStartup) {
                this.connector = connector;
                waitForStartup.notify();
            }
        });

        synchronized (waitForStartup) {
            this.tester.run();

            waitForStartup.wait();
            // at this point the connector is ready; end the setup and start the tests
        }
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        this.tester.close();

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
        return this.connector;
    }

    /**
     * Method to override
     * @return WatchWolf config file
     */
    public File getConfigFile() { throw new UnspecifiedConfigFileException(); }
}
