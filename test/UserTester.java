import com.rogermiranda1000.watchwolf.tester.AbstractTest;
import com.rogermiranda1000.watchwolf.tester.TesterConnector;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.File;

@ExtendWith(UserTester.class) // run the tests with the AbstractTest overridden methods
public class UserTester extends AbstractTest {
    private static final String USER1 = "MinecraftGamer_Z";

    @Override
    public File getConfigFile() {
        return null; // TODO
    }

    @ParameterizedTest
    @ArgumentsSource(UserTester.class)
    public void sendMsg(TesterConnector connector) throws Exception {
        connector.getClientPetition(UserTester.USER1).sendMessage("Hello World!");
        Thread.sleep(8000); // TODO read from message pool
    }
}
