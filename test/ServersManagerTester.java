import com.rogermiranda1000.watchwolf.tester.AbstractTest;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;

/**
 * In the TesterTester we're assuming the server request goes well, so let's not assume that on this tests.
 */
@ExtendWith(ServersManagerTester.class) // run the tests with the AbstractTest overridden methods
public class ServersManagerTester extends AbstractTest {
    @Override
    public File getConfigFile() {
        return null;
    }
}
