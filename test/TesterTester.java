import com.rogermiranda1000.watchwolf.tester.AbstractTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * Alright... We're doing a Tester to test plugins, but who tests the tester to test plugins?
 * That's right! We need a Tester for the Tester.
 */
public class TesterTester extends AbstractTest {
    private static final String USER1 = "rogermiranda1000";

    @Override
    public File getConfigFile() {
        return null; // TODO
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
