import com.rogermiranda1000.watchwolf.entities.ConfigFile;
import com.rogermiranda1000.watchwolf.entities.Map;
import com.rogermiranda1000.watchwolf.entities.Plugin;
import com.rogermiranda1000.watchwolf.entities.ServerType;
import com.rogermiranda1000.watchwolf.tester.Tester;
import com.rogermiranda1000.watchwolf.tester.TesterConnector;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;

/**
 * Alright... We're doing a Tester to test plugins, but who tests the tester to test plugins?
 * That's right! We need a Tester for the Tester.
 */
public class TesterTester {
    private static final String USER1 = "rogermiranda1000";
    public static final String serversManagerIP = "127.0.0.1:8000";
    private static final ServerType serverType = ServerType.Spigot;
    private static final String serverVersion = "1.19";

    private static Tester tester;
    private static TesterConnector connector;

    @BeforeAll
    public static void setup() throws IOException, InterruptedException {
        Socket serversManagerSocket = new Socket("127.0.0.1", 8000); // ServersManager socket

        System.out.println("Starting test for Spigot " + TesterTester.serverVersion);
        TesterTester.tester = new Tester(serversManagerSocket, TesterTester.serverType, TesterTester.serverVersion, new Plugin[]{}, new Map[]{}, new ConfigFile[]{})
                .setOnServerError(Tester.DEFAULT_ERROR_PRINT); // TODO report to JUnit

        final Object waitForStartup = new Object();
        TesterTester.tester.setOnServerStart((connector) -> {
            synchronized (waitForStartup) {
                TesterTester.connector = connector;
                waitForStartup.notify();
            }
        });

        synchronized (waitForStartup) {
            TesterTester.tester.run();

            waitForStartup.wait();
            // at this point the connector is ready; end the setup and start the tests
        }

    }

    @AfterAll
    public static void stop() {
        TesterTester.tester.close();
    }

    @Test
    public void opPlayer() throws Exception {
        // TODO we need Player interface to test it
        TesterTester.connector.opPlayer(TesterTester.USER1);
    }

    @Test
    public void whitelistPlayer() throws Exception {
        // TODO we need Player interface to test it
        TesterTester.connector.whitelistPlayer(TesterTester.USER1);
    }
}
