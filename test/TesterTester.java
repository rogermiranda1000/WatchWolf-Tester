import com.rogermiranda1000.watchwolf.tester.AbstractTest;
import com.rogermiranda1000.watchwolf.tester.TesterConnector;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Alright... We're doing a Tester to test plugins, but who tests the tester to test plugins?
 * That's right! We need a Tester for the Tester.
 */
@ExtendWith(TesterTester.class) // run the tests with the AbstractTest overridden methods
public class TesterTester extends AbstractTest {
    private static final String USER1 = "rogermiranda1000";

    @Override
    public File getConfigFile() {
        return null; // TODO
    }

    @ParameterizedTest
    @ArgumentsSource(TesterTester.class)
    public void opPlayer(TesterConnector connector) throws Exception {
        String clientName = connector.getClients()[0];
        connector.opPlayer(clientName);

        connector.getClientPetition(0).runCommand("kick " + clientName);

        assertFalse(Arrays.asList(connector.getPlayers()).contains(clientName));
    }

    @ParameterizedTest
    @ArgumentsSource(TesterTester.class)
    public void whitelistPlayer(TesterConnector connector) throws Exception {
        // whitelist is automatic; just see if the player is online
        assertTrue(Arrays.asList(connector.getPlayers()).contains(connector.getClients()[0]));
    }

    @ParameterizedTest
    @ArgumentsSource(TesterTester.class)
    public void runCommand(TesterConnector connector) throws Exception {
        final String clientName = connector.getClients()[0];
        connector.runCommand("kick " + clientName);

        assertFalse(Arrays.asList(connector.getPlayers()).contains(clientName));
    }
}
