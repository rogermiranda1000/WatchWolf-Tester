import com.rogermiranda1000.watchwolf.tester.AbstractTest;
import com.rogermiranda1000.watchwolf.tester.TesterConnector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;

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

    @Test
    public void opPlayer(TesterConnector connector) throws Exception {
        // TODO we need Player interface to test it
        connector.opPlayer(TesterTester.USER1);
        System.out.println(connector.getServerVersion());
    }

    @Test
    public void whitelistPlayer(TesterConnector connector) throws Exception {
        // TODO we need Player interface to test it
        connector.whitelistPlayer(TesterTester.USER1);
    }
}
