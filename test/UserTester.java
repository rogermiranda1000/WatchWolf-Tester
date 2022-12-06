import com.rogermiranda1000.watchwolf.client.ClientPetition;
import com.rogermiranda1000.watchwolf.client.MessageNotifier;
import com.rogermiranda1000.watchwolf.entities.Position;
import com.rogermiranda1000.watchwolf.entities.blocks.Block;
import com.rogermiranda1000.watchwolf.entities.blocks.Blocks;
import com.rogermiranda1000.watchwolf.entities.items.Item;
import com.rogermiranda1000.watchwolf.entities.items.ItemType;
import com.rogermiranda1000.watchwolf.tester.AbstractTest;
import com.rogermiranda1000.watchwolf.tester.TesterConnector;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.File;

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

    @ParameterizedTest
    @ArgumentsSource(UserTester.class)
    public void breakBlock(TesterConnector connector) throws Exception {
        String user = UserTester.USER1;
        ClientPetition userPetition = connector.getClientPetition(user);

        Position pos = connector.server.getPlayerPosition(user).add(0, -1, 0);

        Block target_block = Blocks.DIRT;
        connector.setBlock(pos, target_block);

        userPetition.breakBlock(pos);

        assertEquals(Blocks.AIR, connector.server.getBlock(pos));
    }

    /**
     * Break a stone block (test give item & equip item & break block)
     */
    @ParameterizedTest
    @ArgumentsSource(UserTester.class)
    public void equipOnHand(TesterConnector connector) throws Exception {
        String user = UserTester.USER1;
        ClientPetition userPetition = connector.getClientPetition(user);

        Position pos = connector.server.getPlayerPosition(user).add(0, -1, 0);

        Block target_block = Blocks.STONE;
        Item pickaxe = new Item(ItemType.DIAMOND_PICKAXE);
        connector.setBlock(pos, target_block);
        connector.giveItem(user, new Item(ItemType.DIAMOND)); // we don't want to add the pickaxe in the first slot
        connector.giveItem(user, pickaxe);

        userPetition.equipItemInHand(pickaxe);
        userPetition.breakBlock(pos);

        assertEquals(Blocks.AIR, connector.server.getBlock(pos));
    }

    @ParameterizedTest
    @ArgumentsSource(UserTester.class)
    public void moveAt(TesterConnector connector) throws Exception {
        String user = UserTester.USER1;
        ClientPetition userPetition = connector.getClientPetition(user);

        Position pos = connector.server.getPlayerPosition(user).add(1, 0, 0);
        int blocksTraveled = 5;

        // make sure that the player can go to that block
        for (int n = 0; n < blocksTraveled; n++) {
            connector.setBlock(pos, Blocks.AIR);
            connector.setBlock(pos.add(0, 1, 0), Blocks.AIR);
            connector.setBlock(pos.add(0, -1, 0), Blocks.STONE);
            if (n != blocksTraveled-1) pos = pos.add(1, 0, 0);
        }

        userPetition.moveTo(pos);

        Position currentPosition = connector.server.getPlayerPosition(user);
        if (!currentPosition.equals(pos, 0.3f)) assertEquals(pos, currentPosition); // not equal => force error
    }
}
