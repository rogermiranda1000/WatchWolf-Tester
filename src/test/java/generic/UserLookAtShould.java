package generic;

import dev.watchwolf.entities.Position;
import dev.watchwolf.entities.blocks.Blocks;
import dev.watchwolf.tester.AbstractTest;
import dev.watchwolf.tester.ExtendedClientPetition;
import dev.watchwolf.tester.TesterConnector;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(UserLookAtShould.class) // run the tests with the AbstractTest overridden methods
public class UserLookAtShould extends AbstractTest {
    @Override
    public String getConfigFile() {
        return "src/test/java/generic/resources/config.yaml";
    }

    @ParameterizedTest
    @ArgumentsSource(TesterTester.class)
    public void moveTheCameraToTheBlock(TesterConnector connector) throws Exception {
        String clientName = connector.getClients()[0];
        ExtendedClientPetition client = connector.getClientPetition(0);

        Position from = new Position("world", -229.5, 4, -197.5);
        Position []examples = new Position[] {
                new Position("world", -228, 4, -195),
                new Position("world", -230, 4, -195),
                new Position("world", -230, 5, -195),
                new Position("world", -232, 4, -195),
                new Position("world", -230, 4, -199),
                new Position("world", -230, 3, -198),
                new Position("world", -230, 6, -198)
        };
        Float [][]expected = new Float[][] {
                new Float[]{-33f, 20f},
                new Float[]{0f, 20f},
                new Float[]{0f, 2f},
                new Float[]{36f, 20f},
                new Float[]{180f, 60f},
                new Float[]{null, 90f},
                new Float[]{null, -90f}
        };

        // prepare the test area
        connector.server.setBlock(from, Blocks.AIR);
        connector.server.setBlock(from.add(0,1,0), Blocks.AIR);
        connector.server.setBlock(from.add(0,-1,0), Blocks.STONE);
        connector.server.tp(clientName, from);
        // we don't need the blocks that we'll test as it's just a concept (the client will look like there's a block in there)

        for (int i = 0; i < examples.length; i++) {
            moveTheCameraToTheBlock(client, examples[i], expected[i][0], expected[i][1]);
        }
    }

    public void moveTheCameraToTheBlock(ExtendedClientPetition client, Position target, Float expectedYaw, Float expectedPitch) throws Exception {
        client.lookAt(target);

        float yaw = client.getYaw(),
                pitch = client.getPitch();
        System.out.println(yaw + "/" + pitch + " (expected " + (expectedYaw == null ? yaw : expectedYaw) + "/" + (expectedPitch == null ? pitch : expectedPitch) + ")");
    }
}
