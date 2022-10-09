import com.rogermiranda1000.watchwolf.tester.AbstractTest;

import java.io.File;

/**
 * In the TesterTester we're assuming the server request goes well, so let's not assume that on this tests.
 */
public class ServersManagerTester extends AbstractTest {
    @Override
    public File getConfigFile() {
        return null;
    }
}
