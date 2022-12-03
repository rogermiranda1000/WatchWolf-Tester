import com.rogermiranda1000.watchwolf.client.ClientPetition;
import com.rogermiranda1000.watchwolf.client.MessageNotifier;
import com.rogermiranda1000.watchwolf.entities.Position;
import com.rogermiranda1000.watchwolf.entities.blocks.Block;
import com.rogermiranda1000.watchwolf.entities.blocks.Blocks;
import com.rogermiranda1000.watchwolf.tester.AbstractTest;
import com.rogermiranda1000.watchwolf.tester.TesterConnector;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.File;
import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    /**
     * Break the block at the bottom of the player
     */
    @ParameterizedTest
    @ArgumentsSource(UserTester.class)
    public void breakBlock(TesterConnector connector) throws Exception {
        String user = UserTester.USER1;
        ClientPetition userPetition = connector.getClientPetition(user);

        Position pos = connector.server.getPlayerPosition(user).add(0, -1, 0);
        Block target_block = connector.server.getBlock(pos);
        System.out.println("Requested to break " + target_block.toString() + "...");
        userPetition.breakBlock(pos); // TODO what if it's stone?

        // wait till the block breaks (or timeout)
        int timeout = 7_000;
        Instant start = Instant.now();
        while (connector.server.getBlock(pos).equals(target_block) && Duration.between(start, Instant.now()).toMillis() < timeout);

        assertEquals(Blocks.AIR, connector.server.getBlock(pos));
    }
}
