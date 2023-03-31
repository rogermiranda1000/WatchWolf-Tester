package server_starter;

import dev.watchwolf.entities.ServerType;
import dev.watchwolf.tester.AbstractTest;
import dev.watchwolf.tester.Tester;
import dev.watchwolf.tester.TesterConnector;
import generic.UserTester;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

@ExtendWith(ServerStarterShould.class)
public class ServerStarterShould extends AbstractTest {
    private static final int TIMEOUT = 10*60; // 10 minutes to start all the servers

    private ArrayList<String> expected, got;
    private ArrayList<Tester> serverTesters;

    @Override
    public String getConfigFile() {
        return "src/test/java/server_starter/resources/config.yaml";
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws IOException {
        // code inspired by AbstractTest#beforeAll
        this.serverTesters = new ArrayList<>();
        this.expected = new ArrayList<>();
        this.got = new ArrayList<>();

        final Object waitForStartup = new Object();
        for (ServerType serverType : this.fileLoader.getServerTypes()) {
            for (String serverVersion : this.fileLoader.getServerVersions(serverType)) {
                this.expected.add(serverType.name() + " " + serverVersion);

                Socket serversManagerSocket = new Socket(this.fileLoader.getProvider(), 8000),
                        clientsManagerSocket = new Socket(this.fileLoader.getProvider(), 7000); // TODO we won't use any user

                System.out.println("Starting server for " + serverType.name() + " " + serverVersion);
                Tester tester = new Tester(serversManagerSocket, serverType, serverVersion, this.fileLoader.getPlugin(),
                        this.fileLoader.getExtraPlugins(), this.fileLoader.getMaps(), this.fileLoader.getConfigFiles(),
                        clientsManagerSocket, this.fileLoader.getUsers(), this.fileLoader.getOverrideSync(),
                        this.fileLoader.getProvider().equals("127.0.0.1") ? Tester.IP_WSL_MODIFY : Tester.IP_NO_MODIFY)
                        .setOnServerError(Tester.DEFAULT_ERROR_PRINT);

                this.serverTesters.add(tester);

                tester.setOnServerReady((connector) -> {
                    synchronized (waitForStartup) {
                        this.got.add(connector.getServerType().name() + " " + connector.getServerVersion());

                        //server.connector = connector; // TODO why is this needed?
                        waitForStartup.notify();
                    }
                });
            }
        }

        synchronized (waitForStartup) {
            for (Tester server : this.serverTesters) server.run();

            try {
                for (int n = 0; n < this.serverTesters.size(); n++) waitForStartup.wait(TIMEOUT * 1000); // wait n times
                // at this point the connectors are ready; end the setup and start the tests
            } catch (InterruptedException ignore) {} // timedout; leave the loop
        }
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) {
        // code from AbstractTest#afterAll
        for (Tester server : this.serverTesters) server.close();
    }

    @ParameterizedTest
    @ArgumentsSource(UserTester.class)
    public void startupSucceeded(TesterConnector connector) throws Exception {
        if (this.expected.size() == this.got.size()) return; // all ok!

        StringBuilder sb = new StringBuilder();
        sb.append("Expected ").append(this.expected.size()).append(" servers, got ").append(this.got.size()).append(".\n\nDifferences:\n");
        for (String expected : this.expected) {
            boolean isUp = got.contains(expected);
            if (!isUp) sb.append("- ").append(expected).append("\n");
        }
        throw new RuntimeException(sb.toString());
    }
}
