import com.rogermiranda1000.watchwolf.client.MessageNotifier;
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
        String user = UserTester.USER1,
                message = "Hello World!";

        final Object got = new Object();
        MessageNotifier notifier = (author,msg) -> {
            if (author.equals(user) && msg.equals(message)) {
                // notify
                synchronized (got) {
                    got.notify();
                }
            }
        };
        connector.addOnMessage(notifier);
        synchronized (got) {
            connector.getClientPetition(user).sendMessage(message);
            got.wait(4000); // wait 4s
        }
        connector.removeOnMessage(notifier);
    }
}
