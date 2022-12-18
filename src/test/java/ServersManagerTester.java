import dev.watchwolf.tester.AbstractTest;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * In the TesterTester we're assuming the server request goes well, so let's not assume that on this tests.
 */
@ExtendWith(ServersManagerTester.class) // run the tests with the AbstractTest overridden methods
public class ServersManagerTester extends AbstractTest {
    @Override
    public String getConfigFile() {
        return "resources/config.yaml";
    }
}
