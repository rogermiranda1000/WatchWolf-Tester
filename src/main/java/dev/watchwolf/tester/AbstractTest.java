package dev.watchwolf.tester;

import dev.watchwolf.entities.ServerType;
import dev.watchwolf.entities.files.ConfigFile;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class AbstractTest implements TestWatcher, // send feedback
        BeforeAllCallback, AfterAllCallback, // open/close server
        ArgumentsProvider { // send arguments
    private static class ServerInstance {
        public Tester tester;
        public TesterConnector connector;
    }

    private static HashMap<Class<? extends AbstractTest>, AbstractTest> instances = new HashMap<>();

    private ArrayList<ServerInstance> servers;
    private UUID testID;

    protected TestConfigFileLoader fileLoader;

    public AbstractTest() throws ConfigFileException {
        try {
            this.fileLoader = new TestConfigFileLoader(this.getConfigFile());
        } catch (IOException ex) {
            throw new ConfigFileException(ex);
        }
    }

    protected static void addInstance(Class<? extends AbstractTest> cls, AbstractTest instance) {
        AbstractTest.instances.put(cls, instance);
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws IOException {
        AbstractTest.addInstance((Class<? extends AbstractTest>) extensionContext.getTestClass().orElseThrow((Supplier<? extends RuntimeException>) () -> {throw new IllegalArgumentException("Extension context not extends of AbstractTest");}), this);

        this.servers = new ArrayList<>();
        this.testID = UUID.randomUUID();

        final Object waitForStartup = new Object();
        for (ServerType serverType : this.fileLoader.getServerTypes()) {
            for (String serverVersion : this.fileLoader.getServerVersions(serverType)) {
                Socket serversManagerSocket = new Socket(this.fileLoader.getProvider(), 8000), // ServersManager socket TODO change port
                        clientsManagerSocket = new Socket(this.fileLoader.getProvider(), 7000); // ClientsManager socket TODO change port

                final ServerInstance server = new ServerInstance();
                this.servers.add(server);

                System.out.println("Starting server for " + serverType.name() + " " + serverVersion + " using ID " + testID.toString());
                server.tester = new Tester(serversManagerSocket, serverType, serverVersion, this.fileLoader.getPlugin(),
                        this.fileLoader.getExtraPlugins(), this.fileLoader.getWorldType(), this.fileLoader.getDifficulty(),
                        this.fileLoader.getMaps(), this.fileLoader.getConfigFiles(),
                        clientsManagerSocket, this.fileLoader.getUsers(), this.fileLoader.getOverrideSync(),
                        this.fileLoader.getProvider().equals("127.0.0.1") ? Tester.IP_WSL_MODIFY : Tester.IP_NO_MODIFY)
                                .setOnServerError(Tester.DEFAULT_ERROR_PRINT); // TODO report to JUnit

                server.tester.setOnServerReady((connector) -> {
                    // @pre This needs to go before notifying
                    try {
                        if (this.fileLoader.reportTimings()) connector.server.startTimings();

                        this.beforeAll(connector);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    synchronized (waitForStartup) {
                        server.connector = connector;
                        waitForStartup.notify();
                    }
                });
            }
        }

        synchronized (waitForStartup) {
            for (ServerInstance server : this.servers) server.tester.run();

            try {
                for (int n = 0; n < this.servers.size(); n++) waitForStartup.wait(); // wait n times
                // at this point the connectors are ready; end the setup and start the tests
            } catch (InterruptedException ignore) {}
        }
    }

    /**
     * This method will run just once (for each server), at the start. Use it to setup all the tests (e.g. position
     * one player to a specific place, or giving him items)
     * @param server The connector to the server that is being enabled right now
     */
    public void beforeAll(TesterConnector server) throws IOException {}

    @Override
    public void afterAll(ExtensionContext extensionContext) throws IOException {
        for (ServerInstance server : this.servers) {
            TesterConnector connector = server.connector;
            this.afterAll(connector); // let the test close before the server actually stops

            if (this.fileLoader.reportTimings()) {
                System.out.println("Getting timings report... DO NOT STOP the tests.");
                connector.server.stopTimings()
                        .saveToFile(
                                new File(this.fileLoader.getTimingsDirectory(),
                                        "timings-" + connector.getServerType().name() + "-"
                                                + connector.getServerVersion() + ".html")
                        );
            }

            server.tester.close();
        }

        // TODO send 'done' to website
    }

    /**
     * This method will run just once (for each server), at the end. Use it to close resources from all the tests
     * @param server The connector to the server that is being enabled right now
     */
    public void afterAll(TesterConnector server) throws IOException {}

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

    protected static AbstractTest getInstance(Class<?> cls) throws IllegalArgumentException {
        AbstractTest instance = AbstractTest.instances.get(cls);
        if (instance == null) throw new IllegalArgumentException("Instance of " + cls + " not instantiated.");
        return instance;
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return AbstractTest.getInstance(extensionContext.getTestClass().orElse(null)).servers.stream().map(e -> Arguments.of(e.connector));
    }

    /*@Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == TesterConnector.class;
    }*/

    /**
     * Method to override
     * @return WatchWolf config file
     */
    public String getConfigFile() { throw new UnspecifiedConfigFileException(); }
}
