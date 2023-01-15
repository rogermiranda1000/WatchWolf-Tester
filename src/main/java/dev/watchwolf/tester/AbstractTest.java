package dev.watchwolf.tester;

import dev.watchwolf.entities.ServerType;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

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

    private final TestConfigFileLoader fileLoader;

    public AbstractTest() throws ConfigFileException {
        try {
            this.fileLoader = new TestConfigFileLoader(this.getConfigFile());
        } catch (IOException ex) {
            throw new ConfigFileException(ex);
        }
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws IOException {
        AbstractTest.instances.put((Class<? extends AbstractTest>) extensionContext.getTestClass().orElseThrow((Supplier<? extends RuntimeException>) () -> {throw new IllegalArgumentException("Extension context not extends of AbstractTest");}), this);

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
                        this.fileLoader.getExtraPlugins(), this.fileLoader.getMaps(), this.fileLoader.getConfigFiles(),
                        clientsManagerSocket, this.fileLoader.getUsers(), this.fileLoader.getOverrideSync())
                                .setOnServerError(Tester.DEFAULT_ERROR_PRINT); // TODO report to JUnit

                server.tester.setOnServerReady((connector) -> {
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

    @Override
    public void afterAll(ExtensionContext extensionContext) {
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
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        Class<?> cls = extensionContext.getTestClass().orElse(null);
        AbstractTest instance = AbstractTest.instances.get(cls);
        if (instance == null) throw new IllegalArgumentException("Instance of " + cls + " not instantiated.");

        return instance.servers.stream().map(e -> Arguments.of(e.connector));
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
