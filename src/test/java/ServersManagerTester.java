import dev.watchwolf.entities.Position;
import dev.watchwolf.entities.blocks.Blocks;
import dev.watchwolf.tester.AbstractTest;
import dev.watchwolf.tester.TesterConnector;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * In the TesterTester we're assuming the server request goes well, so let's not assume that on this tests.
 */
@ExtendWith(ServersManagerTester.class) // run the tests with the AbstractTest overridden methods
public class ServersManagerTester extends AbstractTest {
    private static final Position WORLD_EXPECTED_PLAYER_POSITION = new Position("world", 1000.5,100,1000.5);

    @Override
    public String getConfigFile() {
        return "src/test/java/resources/servers-manager-config.yaml";
    }

    @ParameterizedTest
    @ArgumentsSource(TesterTester.class)
    public void loadWorld(TesterConnector connector) throws Exception {
        // the world loaded marks the player position with a redstone block; if there's that block, then the world has been loaded
        assertEquals(Blocks.REDSTONE_BLOCK, connector.server.getBlock(WORLD_EXPECTED_PLAYER_POSITION.add(0,-1,0)));
    }
}
