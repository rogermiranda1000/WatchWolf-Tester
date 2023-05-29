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
    public void lookAtTheExactSameSpot(TesterConnector connector) throws Exception {
        ExtendedClientPetition client = connector.getClientPetition(0);

        // all the tests, with [pitch,yaw]
        float [][]tests = new float[][] {
                new float[]{-90, 0}, // top, south
                new float[]{0, 179}, // horizontal, north
                new float[]{0, -179}, // horizontal, north
                new float[]{90,-90} // bottom, east
        };

        for (float []test : tests) {
            float pitch = test[0],
                    yaw = test[1];

            client.lookAt(pitch, yaw);

            Thread.sleep(500); // let the player set the new target

            System.out.println(client.getYaw() + "/" + client.getPitch() + " (expected " + yaw + "/" + pitch + ")");
        }
    }

    @ParameterizedTest
    @ArgumentsSource(TesterTester.class)
    public void moveTheCameraToTheBlock(TesterConnector connector) throws Exception {
        String clientName = connector.getClients()[0];
        ExtendedClientPetition client = connector.getClientPetition(0);

        Position from = new Position("world", -229.5, 4, -197.5);
        Position []examples = new Position[] {
                new Position("world", -227.5, 4.5, -194.5),
                new Position("world", -229.5, 4.5, -194.5),
                new Position("world", -229.5, 5.5, -194.5),
                new Position("world", -231.5, 4.5, -194.5),
                new Position("world", -229.5, 4.5, -198.5),
                new Position("world", -229.5, 3.5, -197.5),
                new Position("world", -229.5, 6.5, -197.5)
        };
        // all the results, with [yaw,pitch]
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

        // let the chunks load
        Thread.sleep(10000);

        for (int i = 0; i < examples.length; i++) {
            moveTheCameraToTheBlock(client, examples[i], expected[i][0], expected[i][1]);
        }
    }

    public void moveTheCameraToTheBlock(ExtendedClientPetition client, Position target, Float expectedYaw, Float expectedPitch) throws Exception {
        client.lookAt(target);
        Thread.sleep(500);

        float yaw = client.getYaw(),
                pitch = client.getPitch();
        System.out.println(yaw + "/" + pitch + " (expected " + (expectedYaw == null ? yaw : expectedYaw) + "/" + (expectedPitch == null ? pitch : expectedPitch) + ")");
    }
}
